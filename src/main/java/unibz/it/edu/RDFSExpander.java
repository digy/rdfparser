package unibz.it.edu;

import java.util.List;

/**
 * Expands the RDF graph with entailed RDFS triplets
 * 
 * @author digy
 * 
 */
public class RDFSExpander extends RDFExpander {

	private final static String rdfsns = "http://www.w3.org/2000/01/rdf-schema#";

	private final static String[][] domains = {
			{ "rdf:type", "rdfs:Resource" }, { "rdfs:domain", "rdf:Property" },
			{ "rdfs:range", "rdf:Property" },
			{ "rdfs:subPropertyOf", "rdf:Property" },
			{ "rdfs:subClassOf", "rdfs:Class" },
			{ "rdf:subject", "rdf:Statement" },
			{ "rdf:predicate", "rdf:Statement" },
			{ "rdf:object", "rdf:Statement" },
			{ "rdfs:member", "rdfs:Resource" }, { "rdf:first", "rdf:List" },
			{ "rdf:rest", "rdf:List" }, { "rdfs:seeAlso", "rdfs:Resource" },
			{ "rdfs:isDefinedBy", "rdfs:Resource" },
			{ "rdfs:comment", "rdfs:Resource" },
			{ "rdfs:label", "rdfs:Resource" },
			{ "rdf:value", "rdfs:Resource" }, };

	private final static String[][] ranges = { { "rdf:type", "rdfs:Class" },
			{ "rdfs:domain", "rdfs:Class" }, { "rdfs:range", "rdfs:Class" },
			{ "rdfs:subPropertyOf", "rdf:Property" },
			{ "rdfs:subClassOf", "rdfs:Class" },
			{ "rdf:subject", "rdfs:Resource" },
			{ "rdf:predicate", "rdfs:Resource" },
			{ "rdf:object", "rdf:Resource" },
			{ "rdfs:member", "rdfs:Resource" },
			{ "rdf:first", "rdfs:Resource" }, { "rdf:rest", "rdf:List" },
			{ "rdfs:seeAlso", "rdfs:Resource" },
			{ "rdfs:isDefinedBy", "rdfs:Resource" },
			{ "rdfs:comment", "rdfs:Resource" },
			{ "rdfs:label", "rdfs:Literal" }, { "rdf:value", "rdfs:Resource" } };
	private final static String[][] subclassof = {
			{ "rdfs:ContainerMembershipProperty", "rdf:Property" },
			{ "rdf:Alt", "rdfs:Container" }, { "rdf:Bag", "rdfs:Container" },
			{ "rdf:Seq", "rdfs:Container" } };

	@Override
	public void build_axioms() {
		super.build_axioms();
		generate_predicates(domains, rdfsns + "rdfs:domain");
		generate_predicates(ranges, rdfsns + "rdfs:range");
		generate_predicates(subclassof, rdfsns + "rdfs:subClassOf");

	}

	private void generate_predicates(String[][] names, String predicate) {
		for (int i = 0; i < names.length; ++i) {
			String sub_name = append_prefix(names[i][0]);
			String obj_name = append_prefix(names[i][1]);

			axiomatic_tiplets.add(new RDFTriplet(new RDFUri(sub_name),
					new RDFUri(predicate), new RDFUri(obj_name)));
		}
	}

	private String append_prefix(String value) {
		String rv = null;
		if (value.startsWith("rdf:")) {
			rv = rdfns + value;
		} else if (value.startsWith("rdfs:")) {
			rv = rdfsns + value;
		}
		return rv;
	}

	@Override
	public void expand(RDFGraph data) {
		boolean cnt = true;
		do {
			cnt = false;
			if (rdf1(data)) {
				cnt = true;
			}
			if (rdfs1(data)) {
				cnt = true;
			}
			if (rdfs2(data)) {
				cnt = true;
			}
			if (rdfs3(data)) {
				cnt = true;
			}
			if (rdfs4(data)) {
				cnt = true;
			}
			if (rdfs5(data)) {
				cnt = true;
			}
			if (rdfs6(data)) {
				cnt = true;
			}
			if (rdfs7(data)) {
				cnt = true;
			}
			if (rdfs8(data)) {
				cnt = true;
			}
			if (rdfs9(data)) {
				cnt = true;
			}
			if (rdfs10(data)) {
				cnt = true;
			}
			if (rdfs11(data)) {
				cnt = true;
			}

		} while (cnt);
	}

	/**
	 * All text strings should have type Literal
	 * 
	 * @param data
	 * @return
	 */
	private boolean rdfs1(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject obj = trp.getObject();
			RDFTriplet _type = new RDFTriplet(obj, new RDFUri(rdfns
					+ "rdf:type"), new RDFUri(rdfsns + "rdfs:Literal"));
			if ((obj instanceof RDFText) && (!triples.contains(_type))) {
				triples.add(_type);
				return true;
			}
		}
		return false;
	}

	private boolean rdfs2(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(new RDFUri(rdfsns + "rdfs:domain"))) {
				for (RDFTriplet trp2 : triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					RDFTriplet _type = new RDFTriplet(sub2, new RDFUri(rdfns+ "rdf:type"), obj);
					if ((pred2.equals(sub)) && (!triples.contains(_type))) {
						triples.add(_type);
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean rdfs3(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(new RDFUri(rdfsns + "rdfs:range"))) {
				for (RDFTriplet trp2 : triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					RDFTriplet _type = new RDFTriplet(obj2, new RDFUri(rdfns+ "rdf:type"), obj);
					if ((pred2.equals(sub)) && (!triples.contains(_type))) {
						triples.add(_type);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Subjects and Objects must be rdfs:Resource
	 * @param data
	 * @return
	 */
	private boolean rdfs4(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			
			RDFObject sub = trp.getSubject();
			RDFObject obj = trp.getObject();
			
			RDFTriplet _res1 = new RDFTriplet(sub, new RDFUri(rdfns + "rdf:type"), new RDFUri(rdfsns + "rdfs:Resource"));
			RDFTriplet _res2 = new RDFTriplet(obj, new RDFUri(rdfns + "rdf:type"), new RDFUri(rdfsns + "rdfs:Resource"));
			if (! triples.contains(_res1)) {
				triples.add(_res1);
				return true;
			} else if (! triples.contains(_res2)) {
				triples.add(_res2);
				return true;
			}	
		}
		return false;	
	}
	
	/**
	 * Subproperties
	 * @param data
	 * @return
	 */
	private boolean rdfs5(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if(pred.equals(new RDFUri(rdfsns + "rdfs:SubPropertyOf"))) {
				for (RDFTriplet trp2: triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					RDFTriplet _sub = new RDFTriplet(sub, new RDFUri(rdfsns+ "rdfs:SubPropertyOf"), obj2);
					if (pred2.equals(new RDFUri(rdfsns+ "rdfs:SubPropertyOf")) &&
						obj.equals(sub2) &&
						! triples.contains(_sub)) {
						triples.add(_sub);
						return true;
					}
				}
			}
		}
		return false;
	}
	private boolean rdfs6(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			RDFTriplet _sub = new RDFTriplet(sub, new RDFUri(rdfsns+ "rdfs:subPropertyOf"), sub);
			
			if (pred.equals(new RDFUri(rdfns+"rdf:type")) &&
					obj.equals(new RDFUri(rdfns + "rdf:Property"))&&
					! triples.contains(_sub)) {
				triples.add(_sub);
				return true;
			}
		}
		return false;
	}
	
	private boolean rdfs7(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(new RDFUri(rdfsns+"rdfs:subPropertyOf"))){
				for (RDFTriplet trp2: triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					RDFTriplet _subprop = new RDFTriplet(sub2, obj, obj2);
					if (sub.equals(pred2) && ! triples.contains(_subprop)) {
						triples.add(_subprop);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean rdfs8(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			RDFTriplet _subclass = new RDFTriplet(sub, 
					new RDFUri(rdfsns + "rdfs:subClassOf") , 
					new RDFUri(rdfns + "rdfs:Resource"));
			if (pred.equals(new RDFUri(rdfns + "rdf:type"))&&
				obj.equals(new RDFUri(rdfsns + "rdfs:Class"))&&
				! triples.contains(_subclass)) {
				triples.add(_subclass);
				return true;	
			}
		}
		return false;
	}
	
	private boolean rdfs9(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(new RDFUri(rdfsns+"rdfs:subClassOf"))) {
				for (RDFTriplet trp2: triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					RDFTriplet _type = new RDFTriplet(sub2, new RDFUri(rdfns + "rdf:type"), obj);
					if (pred2.equals(new RDFUri(rdfns+"rdf:type"))&&
							obj2.equals(sub) &&
							! triples.contains(_type)) {
						triples.add(_type);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean rdfs10(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			RDFTriplet _subclass = new RDFTriplet(sub, new RDFUri(rdfsns+"rdfs:subClassOf") , sub);
			if (pred.equals(new RDFUri(rdfns + "rdf:type"))&&
					obj.equals(new RDFUri(rdfsns+"rdfs:Class"))&&
					! triples.contains(_subclass)) {
				triples.add(_subclass);
				return true;
			}
		}
		return false;
	}
	
	private boolean rdfs11(RDFGraph data) {
		List<RDFTriplet> triples = data.getTriplets();
		for (RDFTriplet trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(new RDFUri(rdfsns+"rdfs:subClassOf"))) {
				for (RDFTriplet trp2: triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					RDFTriplet _ss = new RDFTriplet(sub, new RDFUri(rdfsns+"rdfs:subClassOf"), obj2);
					if (pred2.equals(new RDFUri(rdfsns+"rdfs:subClassOf"))&&
							! triples.contains(_ss)) {
						triples.add(_ss);
						return true;
					}
				}
			}
		}
		return false;
	}
}
