package unibz.it.edu.rdfElements;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

	private List<Tripel> triplets;
	private Map<String, String> ns;

	public Graph(List<Tripel> triplets) {
		this.triplets = triplets;
		ns = new HashMap<String, String>();
	}

	public Graph() {
		triplets = new LinkedList<Tripel>();
		ns = new HashMap<String, String>();
	}

	public void addTriplet_text(String s, String p, String o) {
		Uri _s = new Uri(s);
		Uri _p = new Uri(p);
		Literal _o = new Literal(o);

		for (String name : ns.keySet()) {
			if (s.startsWith(name)) {
				_s = new Uri(s.substring(name.length()), name);
			}
			if (p.startsWith(name)) {
				_p = new Uri(p.substring(name.length()), name);
			}
		}

		triplets.add(new Tripel(_s, _p, _o));
	}

	public void addTriplet_uri(String s, String p, String o) {
		Uri _s = new Uri(s);
		Uri _p = new Uri(p);
		Uri _o = new Uri(o);

		for (String name : ns.keySet()) {
			if (s.startsWith(name)) {
				_s = new Uri(s.substring(name.length()), name);
			}
			if (p.startsWith(name)) {
				_p = new Uri(p.substring(name.length()), name);
			}
			if (o.startsWith(name)) {
				_o = new Uri(o.substring(name.length()), name);
			}
		}
		triplets.add(new Tripel(_s, _p, _o));
	}

	public BNode addTriplet_blank(String s, String p) {
		Uri _s = new Uri(s);
		Uri _p = new Uri(p);
		BNode _o = new BNode();

		for (String name : ns.keySet()) {
			if (s.startsWith(name)) {
				_s = new Uri(s.substring(name.length()), name);
			}
			if (p.startsWith(name)) {
				_p = new Uri(p.substring(name.length()), name);
			}
		}

		triplets.add(new Tripel(_s, _p, _o));
		return _o;
	}

	public void setNamespaces(Map<String, String> ns) {
		this.ns = ns;
	}

	public Map<String, String> getNamespaces() {
		return this.ns;
	}

	public List<Tripel> getTriplets() {
		return triplets;
	}
}
