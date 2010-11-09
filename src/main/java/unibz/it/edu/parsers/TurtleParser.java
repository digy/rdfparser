package unibz.it.edu.parsers;

import java.io.File;
import java.util.List;

import unibz.it.edu.rdfElements.RDFGraph;
import unibz.it.edu.rdfElements.RDFObject;
import unibz.it.edu.rdfElements.RDFTriplet;

public class TurtleParser {
	
	public static RDFGraph decode(File file) {
		return null;
	}
	
	public static StringBuilder encode(RDFGraph data) {
		List<RDFTriplet> triplets = data.getTriplets();
		StringBuilder result = new StringBuilder();
		for (RDFTriplet trp: triplets) {
			RDFObject subject = trp.getSubject();
			RDFObject predicate = trp.getPredicate();
			RDFObject object = trp.getObject();
			result.append(String.format("%s %s %s .\n", subject, predicate, object));
		}
		return result;	
	}

}
