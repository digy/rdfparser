package unibz.it.edu;

import java.util.List;

import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.Tripel;
import unibz.it.edu.rdfElements.Uri;

/**
 * Expands the simple triple graph with entailed RDF triplets
 * 
 * @author digy
 * 
 */
public class RDFExpander extends AbstractExpander {

	protected static final String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

	private static final String[] rdf_property_names = { "type", "subject",
			"predicate", "object", "first", "rest", "value" };

	public RDFExpander() {
		super();
	}

	@Override
	public void build_axioms() {
		for (int i = 0; i < rdf_property_names.length; ++i) {
			axiomatic_tiplets
					.add(new Tripel(new Uri(rdf_property_names[i], rdfns),
							new Uri("type", rdfns), new Uri("Property", rdfns)));
		}
		axiomatic_tiplets.add(new Tripel(new Uri("nil", rdfns), new Uri("type",
				rdfns), new Uri("List", rdfns)));
	}

	@Override
	public void expand(Graph data) {
		add_axioms(data);
		boolean rule_applied = false;
		do {
			rule_applied = rdf1(data);

		} while (rule_applied);
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
		List<Tripel> triplets = data.getTriplets();
		for (Tripel trp : triplets) {
			Uri pred = trp.getPredicate();
			Tripel _type = new Tripel(pred, new Uri("type", rdfns), new Uri(
					"Property", rdfns));
			if (!triplets.contains(_type)) {
				triplets.add(_type);
				return true;
			}
		}
		return false;
	}

}
