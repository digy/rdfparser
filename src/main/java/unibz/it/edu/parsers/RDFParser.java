package unibz.it.edu.parsers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import unibz.it.edu.rdfElements.RDFGraph;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Text;

public class RDFParser {
	private RDFGraph data;

	public RDFParser() {
		data = new RDFGraph();
	}

	/**
	 * Transform the RDF file @param file to internal representation @class
	 * RDFGraph
	 * 
	 * @param file
	 * @return
	 */

	public RDFGraph decode(File file) {

		try {
			Builder parser = new Builder();
			Document doc = parser.build(file);
			Element root = doc.getRootElement();

			// Getting all namespaces
			int nsCount = root.getNamespaceDeclarationCount();
			Map<String, String> ns = new HashMap<String, String>(nsCount);

			for (int i = 0; i < nsCount; ++i) {
				String prefix_name = root.getNamespacePrefix(i);
				String full_name = root.getNamespaceURI(prefix_name);
				ns.put(full_name, prefix_name);
			}
			data.setNamespaces(ns);
			Elements subjects = root.getChildElements();
			for (int i = 0; i < subjects.size(); i++) {
				Element subject = subjects.get(i);
				parseTriplet(subject);
			}
		} catch (ParsingException ex) {
			System.err.println("Malformed XML");
		} catch (IOException ex) {
			System.err.println("Could not read file");
		}
		return data;
	}

	private void parseTriplet(Element subject) {

		parseAttributes(subject);
		parseInnerNodes(subject);
	}

	/**
	 * Attributes inside subject element form predicates, excluding rdf:about.
	 * Values of the attributes are interpreted as literals (XXX not sure)
	 * 
	 * @param subject
	 */
	private void parseAttributes(Element subject) {
		String subject_uri = extractURI(subject);

		for (int i = 0; i < subject.getAttributeCount(); i++) {
			Attribute attr = subject.getAttribute(i);

			if (!attr.getQualifiedName().equals("rdf:about")) {
				String object_uri = attr.getValue();
				String predicate_uri = attr.getNamespaceURI()
						+ attr.getLocalName();
				data.addTriplet_text(subject_uri, predicate_uri, object_uri);
			}
		}
	}

	private void parseInnerNodes(Element subject) {
		String subject_uri = extractURI(subject);

		Elements predicates = subject.getChildElements();
		for (int i = 0; i < predicates.size(); i++) {
			Element predicate = predicates.get(i);
			String predicate_uri = predicate.getNamespaceURI()
					+ predicate.getLocalName();

			if (is_object_text(predicate)) {
				data.addTriplet_text(subject_uri, predicate_uri, predicate
						.getChild(0).getValue());
			} else if (is_object_attr(predicate)) {
				Attribute attr = predicate.getAttribute("resource",
						"http://www.w3.org/1999/02/22-rdf-syntax-ns#");
				String object_uri = attr.getValue();
				data.addTriplet_uri(subject_uri, predicate_uri, object_uri);
			} else if (is_blank_node(predicate)) {
				

			} else {
				Elements objects = predicate.getChildElements();
				for (int j = 0; j < objects.size(); j++) {
					Element object = objects.get(j);
					String object_uri = extractURI(object);
					data.addTriplet_uri(subject_uri, predicate_uri, object_uri);
					parseTriplet(object);
				}
			}
		}
	}
	
	private boolean is_blank_node(Element predicate) {
		Attribute attr = predicate.getAttribute("parseType", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		if (attr == null) {
			return false;
		}
		return true;
		
	}

	private boolean is_object_text(Element predicate) {
		return ((predicate.getChildCount() == 1) && (predicate.getChild(0) instanceof Text));
	}

	private boolean is_object_attr(Element predicate) {
		Attribute attr = predicate.getAttribute("resource",
				"http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		if (attr != null) {
			return true;
		}
		return false;
	}

	/**
	 * Extract the full URI from an Element
	 **/
	private String extractURI(Element element) {
		Attribute attr = element.getAttribute("about",
				"http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		return attr.getValue();
	}

	/**
	 * Output @param data as an RDF/XML document
	 * 
	 * @param data
	 */
	public static void encode(RDFGraph data) {

	}

}
