package unibz.it.edu.parsers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Text;
import unibz.it.edu.rdfElements.BNode;
import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.Literal;
import unibz.it.edu.rdfElements.RDFObject;
import unibz.it.edu.rdfElements.Uri;
import unibz.it.edu.terms.Rdf;
import unibz.it.edu.terms.XML;

public class RDFXMLParser {
	private Graph data;

	public RDFXMLParser() {
		data = new Graph();
	}

	/**
	 * Transform the RDF file @param file to internal representation @class
	 * RDFGraph
	 * 
	 * @param file
	 * @return
	 */

	public Graph decode(File file) {

		try {
			Builder parser = new Builder();
			Document doc = parser.build(file);
			Element root = doc.getRootElement();

			// Getting all namespaces
			parseNamespaces(root);

			Elements subjects = root.getChildElements();
			for (int i = 0; i < subjects.size(); i++) {
				Element subject = subjects.get(i);
				parseSubject(subject);
			}
		} catch (ParsingException ex) {
			System.err.println("Malformed XML");
		} catch (IOException ex) {
			System.err.println("Could not read file");
		}
		return data;
	}

	/**
	 * Adds namespaces and their prefixes to RDFGraph element
	 * 
	 * @param root
	 */
	private void parseNamespaces(Element root) {
		int nsCount = root.getNamespaceDeclarationCount();
		Map<String, String> ns = new HashMap<String, String>(nsCount);

		for (int i = 0; i < nsCount; ++i) {
			String prefix_name = root.getNamespacePrefix(i);
			String full_name = root.getNamespaceURI(prefix_name);
			ns.put(prefix_name, full_name);
		}
		data.setNamespaces(ns);
	}

	private RDFObject parseSubject(Element subject) {
		RDFObject s = null;
		
		if (subject.getAttribute("about", Rdf.ns) != null) {
			s = new Uri(subject.getAttributeValue("about", Rdf.ns));
		} else {
			s = new BNode();
		}
		if (! (subject.getNamespaceURI().equals(Rdf.ns) &&
				subject.getLocalName().equals("Description"))) {
			data.addTriple(s, Rdf.type, new Uri(subject.getNamespaceURI()+subject.getLocalName()));	
		}

		String lang = subject.getAttributeValue("lang", XML.ns);

		for (int i = 0; i < subject.getAttributeCount(); i++) {
			Attribute attr = subject.getAttribute(i);
			if (!attr.getNamespaceURI().equals(Rdf.ns)&&
					!attr.getNamespaceURI().equals(XML.ns)) {
				
				Uri p = new Uri(attr.getNamespaceURI() + attr.getLocalName());
				Literal o = new Literal(attr.getValue());
				o.setLang(lang);
				data.addTriple(s, p, o);
			}
		}
		
		
		Elements predicates = subject.getChildElements();
		for (int i = 0; i < predicates.size(); i++) {
			Element predicate = predicates.get(i);
			parsePredicate(s, predicate, i);
		}
		return s;
	}

	private void parsePredicate(RDFObject s, Element predicate, int siblingCount) {

		String uri_name = predicate.getNamespaceURI()
				+ predicate.getLocalName();
		Uri p = new Uri(uri_name);

		if (p.equals(Rdf.li)) {
			p = new Uri(Rdf.ns + String.format("_%s", siblingCount+1));
		}
		
		String resource = predicate.getAttributeValue("resource", Rdf.ns);
		if (resource != null) {
			data.addTriple(s, p, new Uri(resource));
			return;
		}

		String parseType = predicate.getAttributeValue("parseType", Rdf.ns);
		if (parseType != null) {
			if (parseType.equals("Resource")) {
				// BNode;
				BNode o = new BNode();
				data.addTriple(s, p, o);
				
				Elements bnodes_predicates = predicate.getChildElements();
				for (int i = 0; i < bnodes_predicates.size(); ++i) {
					parsePredicate(o, bnodes_predicates.get(i), i);
				}
				
			} else if (parseType.equals("Literal")) {
				// XMLLiteral
				StringBuilder inner_nodes = new StringBuilder();
				for (int i=0; i < predicate.getChildCount(); ++i) {
					inner_nodes.append(predicate.getChild(i).toXML());
				}
				
				Literal o = new Literal(inner_nodes.toString());
				o.setDatatype(Rdf.XMLLiteral);
				data.addTriple(s, p, o);
			} else if (parseType.equals("Collection")) {
				BNode o = new BNode();
				data.addTriple(s, p, o);
				
				Elements collection_elems = predicate.getChildElements();
				for (int i = 0; i < collection_elems.size(); ++i) {
					RDFObject elem = parseSubject(collection_elems.get(i));
					data.addTriple(o, Rdf.first, elem);
					
					if (i < collection_elems.size() - 1) {
						BNode rest = new BNode();
						data.addTriple(o, Rdf.rest, rest);
						o = rest;
					} else {
						// Last element must be rdf:nill
						data.addTriple(o, Rdf.rest, Rdf.nil);
					}
				}	
			}
			return;
		}

		if (is_object_text(predicate)) {
			String datatype = predicate.getAttributeValue("datatype", Rdf.ns);
			Uri dt = null;
			String lang = null;
			if (datatype != null) {
				dt = new Uri(datatype);
			} else {
				lang = predicate.getAttributeValue("lang", XML.ns);
			}
			Literal o = new Literal(predicate.getChild(0).getValue());
			o.setDatatype(dt);
			o.setLang(lang);
			data.addTriple(s, p, o);
		} else {
			Elements objects = predicate.getChildElements();
			for (int i = 0; i < objects.size(); ++i) {
				RDFObject o = parseSubject(objects.get(i));
				data.addTriple(s, p, o);
			}
		}
	}

	
	private boolean is_object_text(Element predicate) {
		return ((predicate.getChildCount() == 1) && (predicate.getChild(0) instanceof Text));
	}
	
	/**
	 * Output @param data as an RDF/XML document
	 * 
	 * @param data
	 */
	public static void encode(Graph data) {

	}

}
