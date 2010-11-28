package unibz.it.edu.entailment;

import java.util.List;

import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.RDFObject;
import unibz.it.edu.rdfElements.Triple;
import unibz.it.edu.rdfElements.Uri;
import unibz.it.edu.terms.Rdf;
/**
 * Expands the simple triple graph with entailed RDF triplets
 * 
 * @author digy
 * 
 */
public class RDFEntailment {


	private static final Uri[] rdf_property_names = 
	{ Rdf.type, Rdf.subject, Rdf.predicate, Rdf.first, Rdf.rest, Rdf.value };


	/**
	 * Expand the graph with the axiomatic triples
	 * @param data
	 * @return
	 */
	public static Graph exapnd_axioms(Graph data) {
		
		for (int i = 0; i < rdf_property_names.length; ++i) {
			data.addTriple(rdf_property_names[i], Rdf.type, Rdf.Property);
		}
		data.addTriple(Rdf.nil, Rdf.type, Rdf.List);
		return data;
	}

	public static Graph expand(Graph data) {

		boolean cnt = true;
		while (cnt) {
			cnt = false;
			if (rdf1(data)) {
				cnt = true;
			}
		}
		return data;
	}

	/**
	 * All predicates must have type Property
	 * 
	 * @param data
	 *            - RDF graph
	 * @return true if the rule was successfully applied and a triple was added
	 *         to the graph
	 */
	private static boolean rdf1(Graph data) {
		List<Triple> triplets = data.getTriples();
		for (Triple trp : triplets) {
			RDFObject pred = trp.getPredicate();
			Triple _type = new Triple(pred, Rdf.type, Rdf.Property);
			if (!triplets.contains(_type)) {
				triplets.add(_type);
				return true;
			}
		}
		return false;
	}
}
