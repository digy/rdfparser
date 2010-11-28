package unibz.it.edu.rdfElements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

	private List<Triple> triples;
	private Map<String, String> ns;

	public Graph(List<Triple> triples) {
		this.triples = triples;
		ns = new HashMap<String, String>();
	}

	public Graph() {
		triples = new LinkedList<Triple>();
		ns = new HashMap<String, String>();
	}
	
	public void addTriple(RDFObject s, RDFObject p, RDFObject o) {
		triples.add(new Triple(s, p, o));
	}

	public void setNamespaces(Map<String, String> ns) {
		this.ns = ns;
	}

	public Map<String, String> getNamespaces() {
		return this.ns;
	}

	public List<Triple> getTriples() {
		return triples;
	}
}
