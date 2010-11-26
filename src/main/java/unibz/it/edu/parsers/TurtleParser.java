package unibz.it.edu.parsers;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.Literal;
import unibz.it.edu.rdfElements.RDFObject;
import unibz.it.edu.rdfElements.Tripel;
import unibz.it.edu.rdfElements.Uri;

public class TurtleParser {

	public static Graph decode(File file) {
		return null;
	}

	public static StringBuilder encode(Graph data) {

		StringBuilder result = new StringBuilder();

		Map<String, String> ns = data.getNamespaces();
		for (String name : ns.keySet()) {
			result.append(String.format("@prefix %s: <%s>\n", ns.get(name),
					name));
		}

		List<Tripel> triplets = data.getTriplets();

		Map<Uri, List<Tripel>> subject_groups = new HashMap<Uri, List<Tripel>>();
		for (Tripel trp : triplets) {
			if (subject_groups.containsKey(trp.getSubject())) {
				subject_groups.get(trp.getSubject()).add(trp);
			} else {
				subject_groups.put(trp.getSubject(), new LinkedList<Tripel>());
				subject_groups.get(trp.getSubject()).add(trp);
			}
		}
		for (Uri sub : subject_groups.keySet()) {
			result.append(toPrefix(sub, ns));
			for (Tripel trp : subject_groups.get(sub)) {
				String pred = toPrefix(trp.getPredicate(), ns);
				String obj = _toPrefix(trp.getObject(), ns);
				result.append(" ");
				result.append(pred);
				result.append(" ");
				result.append(obj);
				result.append(" ;\n");
			}
			result.replace(result.length() - 2, result.length() - 1, ".");
		}
		return result;
	}

	private static String toPrefix(Uri elem, Map<String, String> ns) {
		if (ns.containsKey(elem.getNamespace())) {
			return ns.get(elem.getNamespace()) + ":" + elem.getValue();
		} else {
			return String
					.format("<%s%s>", elem.getNamespace(), elem.getValue());
		}
	}

	private static String _toPrefix(RDFObject elem, Map<String, String> ns) {
		if (elem instanceof Uri) {
			return toPrefix((Uri) elem, ns);
		} else if (elem instanceof Literal) {
			System.out.println(elem);
			return String.format("\"%s\"", elem.getValue());
		}
		return ""; // Just to shut up java compiler
	}

}
