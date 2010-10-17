package unibz.it.edu;

import java.util.LinkedList;
import java.util.List;

public class RDFGraph {
	
	private List<RDFTriplet> triplets = new LinkedList<RDFTriplet>();
	
	public void addTriplet(String s, String p, String o) {
		
		triplets.add(new RDFTriplet(new RDFObject(s), new RDFObject(p), new RDFObject(o)));
	}

	
	public List<RDFTriplet> getTriplets() {
		return triplets;		
	}
}
