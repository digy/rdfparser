package unibz.it.edu;

import java.util.List;

import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.Triple;
import unibz.it.edu.rdfElements.Uri;
import unibz.it.edu.terms.Rdf;
/**
 * Expands the simple triple graph with entailed RDF triplets
 * 
 * @author digy
 * 
 */
public class RDFExpander {


	private static final Uri[] rdf_property_names = 
	{ Rdf.type, Rdf.subject, Rdf.predicate, Rdf.first, Rdf.rest, Rdf.value };


	/**
	 * Expand the graph with the axiomatic triples
	 * @param data
	 * @return
	 */
	public Graph exapnd_axioms(Graph data) {
		
		for (int i = 0; i < rdf_property_names.length; ++i) {
			data.addTriple(rdf_property_names[i], Rdf.type, Rdf.Property);
		}
		data.addTriple(Rdf.nil, Rdf.type, Rdf.List);
		return data;
	}

	public Graph expand(Graph data) {

		boolean rule_applied = false;
		do {
			rule_applied = rdf1(data);

		} while (rule_applied);
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
	protected boolean rdf1(Graph data) {
		List<Triple> triplets = data.getTriples();
		for (Triple trp : triplets) {
			Uri pred = trp.getPredicate();
			Triple _type = new Triple(pred, Rdf.type, Rdf.Property);
			if (!triplets.contains(_type)) {
				triplets.add(_type);
				return true;
			}
		}
		return false;
	}
}
