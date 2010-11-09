package unibz.it.edu;

import java.util.LinkedList;
import java.util.List;

import unibz.it.edu.rdfElements.RDFGraph;
import unibz.it.edu.rdfElements.RDFTriplet;

public abstract class AbstractExpander {


	protected static final List<RDFTriplet> axiomatic_tiplets = new LinkedList<RDFTriplet>();
	
	public AbstractExpander() {
		build_axioms();
	}
	
	protected abstract void build_axioms();
	protected abstract void expand(RDFGraph data);
	
	public void add_axioms(RDFGraph data) {
		List<RDFTriplet> triplets = data.getTriplets();
		for (RDFTriplet trp : axiomatic_tiplets) {
			triplets.add(trp);
		}
	}

}
