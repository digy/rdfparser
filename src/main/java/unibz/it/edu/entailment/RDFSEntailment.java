package unibz.it.edu.entailment;

import java.util.List;

import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.RDFObject;
import unibz.it.edu.rdfElements.Triple;
import unibz.it.edu.rdfElements.Uri;
import unibz.it.edu.terms.Rdf;
import unibz.it.edu.terms.Rdfs;

/**
 * Expands the RDF graph with entailed RDFS triplets
 * 
 * @author digy
 * 
 */
public class RDFSEntailment {


	private final static Uri[][] axioms = {
			{ Rdf.type, Rdfs.domain, Rdfs.Resource },
			{ Rdfs.domain, Rdfs.domain, Rdf.Property },
			{ Rdfs.range, Rdfs.domain, Rdf.Property },
			{ Rdfs.subPropertyOf, Rdfs.domain, Rdf.Property },
			{ Rdfs.subClassOf, Rdfs.domain, Rdfs.Class },
			{ Rdf.subject, Rdfs.domain, Rdf.Statement},
			{ Rdf.predicate, Rdfs.domain, Rdf.Statement },
			{ Rdf.object, Rdfs.domain, Rdf.Statement },
			{ Rdfs.member, Rdfs.domain, Rdfs.Resource },
			{ Rdf.first, Rdfs.domain, Rdf.List },
			{ Rdf.rest, Rdfs.domain, Rdf.List },
			{ Rdfs.seeAlso, Rdfs.domain, Rdfs.Resource },
			{ Rdfs.isDefinedBy, Rdfs.domain, Rdfs.Resource },
			{ Rdfs.comment, Rdfs.domain, Rdfs.Resource},
			{ Rdfs.label, Rdfs.domain, Rdfs.Resource},
			{ Rdf.value, Rdfs.domain, Rdfs.Resource },

			{ Rdf.type, Rdfs.range, Rdfs.Class },
			{ Rdfs.domain, Rdfs.range, Rdfs.Class },
			{ Rdfs.range, Rdfs.range, Rdfs.Class },
			{ Rdfs.subPropertyOf, Rdfs.range, Rdf.Property },
			{ Rdfs.subClassOf, Rdfs.range, Rdfs.Class},
			{ Rdf.subject, Rdfs.range, Rdfs.Resource},
			{ Rdf.predicate, Rdfs.range, Rdfs.Resource },
			{ Rdf.object, Rdfs.range, Rdfs.Resource },
			{ Rdfs.member, Rdfs.range, Rdfs.Resource },
			{ Rdf.first, Rdfs.range, Rdfs.Resource},
			{ Rdf.rest, Rdfs.range, Rdf.List },
			{ Rdfs.seeAlso, Rdfs.range, Rdfs.Resource},
			{ Rdfs.isDefinedBy, Rdfs.range, Rdfs.Resource },
			{ Rdfs.comment, Rdfs.range, Rdfs.Resource },
			{ Rdfs.label, Rdfs.range, Rdfs.Literal },
			{ Rdf.value, Rdfs.range, Rdfs.Resource },

			{ Rdfs.ContainerMembershipProperty, Rdfs.subClassOf, Rdf.Property },
			{ Rdf.Alt, Rdfs.subClassOf, Rdfs.Container},
			{ Rdf.Bag, Rdfs.subClassOf, Rdfs.Container },
			{ Rdf.Seq, Rdfs.subClassOf, Rdfs.Container } };

	/**
	 * Expand the graph with the axiomatic triples
	 * @param data
	 * @return
	 */
	public static Graph exapnd_axioms(Graph data) {
		for (int i = 0; i < axioms.length; ++i) {
			data.addTriple(axioms[i][0], axioms[i][1], axioms[i][2]);
		}
		return data;
	}

	
	public static Graph expand(Graph data) {
		boolean cnt = true;
		do {
			cnt = false;
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
		return data;
	}

	public static boolean rdfs2(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(Rdfs.domain)) {
				for (Triple trp2 : triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					Triple _type = new Triple(sub2,Rdf.type, obj);
					if ((pred2.equals(sub)) && (!triples.contains(_type))) {
						triples.add(_type);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean rdfs3(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(Rdfs.range)) {
				for (Triple trp2 : triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					Triple _type = new Triple(obj2, Rdf.type, obj);
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
	 * 
	 * @param data
	 * @return
	 */
	public static boolean rdfs4(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {

			RDFObject sub = trp.getSubject();
			RDFObject obj = trp.getObject();

			Triple _res1 = new Triple(sub, Rdf.type, Rdfs.Resource);
			Triple _res2 = new Triple(obj, Rdf.type, Rdfs.Resource);
			if (!triples.contains(_res1)) {
				triples.add(_res1);
				return true;
			} else if (!triples.contains(_res2)) {
				triples.add(_res2);
				return true;
			}
		}
		return false;
	}

	/**
	 * Subproperties
	 * 
	 * @param data
	 * @return
	 */
	public static boolean rdfs5(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(Rdfs.subPropertyOf)) {
				for (Triple trp2 : triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					Triple _sub = new Triple(sub, Rdfs.subPropertyOf, obj2);
					if (pred2.equals(Rdfs.subPropertyOf)
							&& obj.equals(sub2) && !triples.contains(_sub)) {
						triples.add(_sub);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean rdfs6(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			Triple _sub = new Triple(sub,Rdfs.subPropertyOf, sub);

			if (pred.equals(Rdf.type)
					&& obj.equals(Rdf.Property)
					&& !triples.contains(_sub)) {
				triples.add(_sub);
				return true;
			}
		}
		return false;
	}

	public static boolean rdfs7(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(Rdfs.subPropertyOf)) {
				for (Triple trp2 : triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					Triple _subprop = new Triple(sub2, obj, obj2);
					if (sub.equals(pred2) && !triples.contains(_subprop)) {
						triples.add(_subprop);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean rdfs8(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			Triple _subclass = new Triple(sub, Rdfs.subClassOf, Rdfs.Resource);
			if (pred.equals(Rdf.type)
					&& obj.equals(Rdfs.Class)
					&& !triples.contains(_subclass)) {
				triples.add(_subclass);
				return true;
			}
		}
		return false;
	}

	public static boolean rdfs9(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(Rdfs.subClassOf)) {
				for (Triple trp2 : triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					Triple _type = new Triple(sub2,Rdf.type, obj);
					if (pred2.equals(Rdf.type) && obj2.equals(sub)
							&& !triples.contains(_type)) {
						triples.add(_type);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean rdfs10(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			Triple _subclass = new Triple(sub, Rdfs.subClassOf, sub);
			if (pred.equals(Rdf.type)
					&& obj.equals(Rdfs.Class)
					&& !triples.contains(_subclass)) {
				triples.add(_subclass);
				return true;
			}
		}
		return false;
	}

	public static boolean rdfs11(Graph data) {
		List<Triple> triples = data.getTriples();
		for (Triple trp : triples) {
			RDFObject sub = trp.getSubject();
			RDFObject pred = trp.getPredicate();
			RDFObject obj = trp.getObject();
			if (pred.equals(Rdfs.subClassOf)) {
				for (Triple trp2 : triples) {
					RDFObject sub2 = trp2.getSubject();
					RDFObject pred2 = trp2.getPredicate();
					RDFObject obj2 = trp2.getObject();
					Triple _ss = new Triple(sub, Rdfs.subClassOf, obj2);
					if (pred2.equals(Rdfs.subClassOf) && obj.equals(sub2)
							&& !triples.contains(_ss)) {
						triples.add(_ss);
						return true;
					}
				}
			}
		}
		return false;
	}
}
