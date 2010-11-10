package unibz.it.edu.rdfElements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class RDFGraph {
	
	private List<RDFTriplet> triplets;
	private Map<String, String> ns;
	
	public RDFGraph(List<RDFTriplet> triplets) {
		this.triplets = triplets;
		ns = new HashMap<String, String>();
	}
	public RDFGraph() {
		triplets = new LinkedList<RDFTriplet>();
		ns = new HashMap<String, String>();
	}
	
	public void addTriplet_text(String s, String p, String o){
		RDFUri _s = new RDFUri(s);
		RDFUri _p = new RDFUri(p);
		RDFLiteral _o = new RDFLiteral(o);
		
		for (String name : ns.keySet()) {
			if (s.startsWith(name)) {
				_s = new RDFUri(s.substring(name.length()), name);
			}
			if (p.startsWith(name)) {
				_p = new RDFUri(p.substring(name.length()), name);
			}
		}
		
		triplets.add(new RDFTriplet(_s, _p, _o));
	}
	
	public void addTriplet_uri(String s, String p, String o){
		RDFUri _s = new RDFUri(s);
		RDFUri _p = new RDFUri(p);
		RDFUri _o = new RDFUri(o);
		
		for (String name : ns.keySet()) {
			if (s.startsWith(name)) {
				_s = new RDFUri(s.substring(name.length()), name);
			}
			if (p.startsWith(name)) {
				_p = new RDFUri(p.substring(name.length()), name);
			}
			if (o.startsWith(name)) {
				_o = new RDFUri(o.substring(name.length()), name);
			}
		}
		triplets.add(new RDFTriplet(_s, _p, _o));
	}

	public void setNamespaces(Map<String, String> ns) {
		this.ns = ns;
	}
	
	public Map<String, String> getNamespaces() {
		return this.ns;
	}
	
	public List<RDFTriplet> getTriplets() {
		return triplets;		
	}
}
