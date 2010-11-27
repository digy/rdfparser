package unibz.it.edu.parsers;

import java.io.File;
import java.util.List;

import unibz.it.edu.rdfElements.BNode;
import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.Literal;
import unibz.it.edu.rdfElements.RDFObject;
import unibz.it.edu.rdfElements.Triple;
import unibz.it.edu.rdfElements.Uri;

public class TurtleParser {

	public static Graph decode(File file) {
		return null;
	}

	public static StringBuilder encode(Graph data) {

		StringBuilder result = new StringBuilder();
		return result;
	}

	public static StringBuilder simpleEncode(Graph data) {
		StringBuilder result = new StringBuilder();
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			String sub = _toPrefix(trp.getSubject());
			String pred = _toPrefix(trp.getPredicate());
			String obj = _toPrefix(trp.getObject());
			result.append(sub);
			result.append(" ");
			result.append(pred);
			result.append(" ");
			result.append(obj);
			result.append(" .\n");
		}
		return result;
	}

	private static String _toPrefix(RDFObject elem) {
		if (elem instanceof Uri) {
			return String.format("<%s>", elem.toString());
		} else if (elem instanceof Literal) {
			Literal lit = (Literal) elem;
			String dt = "";
			String lang = "";

			if (lit.getDatatype() != null) {
				dt = String.format("^^<%s>", lit.getDatatype());
			} else if (lit.getLang() != null) {
				lang = String.format("@%s", lit.getLang());
			}
			String rv = String.format("\"%s\"%s%s", lit.getValue(), dt, lang);
			return rv;
		} else if (elem instanceof BNode) {
			return String.format("_:%s", elem.toString());
		}
		return ""; // Just to shut up java compiler
	}

}
