package unibz.it.edu;

import java.util.LinkedList;
import java.util.List;

import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.Tripel;

public abstract class AbstractExpander {

	protected static final List<Tripel> axiomatic_tiplets = new LinkedList<Tripel>();

	public AbstractExpander() {
		build_axioms();
	}

	protected abstract void build_axioms();

	protected abstract void expand(Graph data);

	public void add_axioms(Graph data) {
		List<Tripel> triplets = data.getTriplets();
		for (Tripel trp : axiomatic_tiplets) {
			triplets.add(trp);
		}
	}

}
