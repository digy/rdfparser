package unibz.it.edu;

import java.util.List;

import unibz.it.edu.rdfElements.RDFGraph;
import unibz.it.edu.rdfElements.RDFTriplet;
import unibz.it.edu.rdfElements.RDFUri;

/**
 * Expands the simple triple graph with entailed RDF triplets
 * 
 * @author digy
 * 
 */
public class RDFExpander extends AbstractExpander {
	
	protected static final String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

	private static final String[] rdf_property_names = { "type", "subject", "predicate",
			"object", "first", "rest", "value" };

	public RDFExpander() {
		super();
	}

	@Override
	public void build_axioms(){	
		for (int i=0; i < rdf_property_names.length; ++i) {
			axiomatic_tiplets.add(new RDFTriplet(
					new RDFUri(rdf_property_names[i], rdfns),
					new RDFUri("type", rdfns),
					new RDFUri("Property", rdfns)));	
		}
		axiomatic_tiplets.add(new RDFTriplet(
				new RDFUri("nil", rdfns),
				new RDFUri("type", rdfns),
				new RDFUri("List", rdfns)));
	}
	
	@Override
	public void expand(RDFGraph data) {
		add_axioms(data);
		boolean rule_applied = false; 
		do {
			rule_applied = rdf1(data);
			
		} while (rule_applied);
	}
	
	
	/**
	 * All predicates must have type Property
	 * @param data - RDF graph
	 * @return true if the rule was successfully applied and a triple was added to the graph
	 */
	protected boolean rdf1(RDFGraph data) {
		List<RDFTriplet> triplets = data.getTriplets();
		for (RDFTriplet trp : triplets) {
			RDFUri pred = trp.getPredicate();
			RDFTriplet _type = new RDFTriplet(pred, new RDFUri("type", rdfns), new RDFUri("Property", rdfns));
			if (! triplets.contains(_type)) {
				triplets.add(_type);
				return true;
			}
		}
		return false;
	}
	
}
