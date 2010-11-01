package unibz.it.edu;

import java.util.LinkedList;
import java.util.List;

public class RDFGraph {
	
	private List<RDFTriplet> triplets;
	
	public RDFGraph(List<RDFTriplet> triplets) {
		this.triplets = triplets;
	}
	public RDFGraph() {
		triplets = new LinkedList<RDFTriplet>();
	}
	
	public void addTriplet_text(String s, String p, String o){
		triplets.add(new RDFTriplet(new RDFUri(s), new RDFUri(p), new RDFText(o)));
	}
	
	public void addTriplet_uri(String s, String p, String o){
		triplets.add(new RDFTriplet(new RDFUri(s), new RDFUri(p), new RDFUri(o)));
	}
	
	public void addTriplet(String s, String p, String o) {
		
		triplets.add(new RDFTriplet(new RDFObject(s), new RDFObject(p), new RDFObject(o)));
	}

	
	public List<RDFTriplet> getTriplets() {
		return triplets;		
	}
}
