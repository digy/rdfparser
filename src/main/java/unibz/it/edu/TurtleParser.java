package unibz.it.edu;

import java.io.File;
import java.util.List;

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
