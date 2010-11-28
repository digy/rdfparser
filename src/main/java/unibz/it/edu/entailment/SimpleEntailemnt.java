package unibz.it.edu.entailment;

import java.util.List;

import unibz.it.edu.rdfElements.BNode;
import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.RDFObject;
import unibz.it.edu.rdfElements.Triple;

public class SimpleEntailemnt {

	public static Graph expand(Graph data) {
		boolean cnt = true;
		while (cnt) {
			cnt = false;
			if (se1(data)) {
				cnt = true;
			}
			if (se2(data)) {
				cnt = true;
			}
		}
		return data;
	}

	public static boolean se2(Graph data) {
		List<Triple> triplets = data.getTriples();
		for (Triple trp : triplets) {
			RDFObject sub = trp.getSubject();

			if ((sub instanceof BNode && ((BNode) sub).parent != null)) {
				continue;
			} else {
				BNode b_sub = null;
				for (Triple trp2 : triplets) {
					RDFObject s = trp2.getSubject();

					if ((s instanceof BNode) && (((BNode) s).parent != null)
							&& (((BNode) s).parent.equals(sub))) {
						b_sub = (BNode) s;
					}

					RDFObject o = trp2.getObject();

					if ((o instanceof BNode) && (((BNode) o).parent != null)
							&& (((BNode) o).parent.equals(sub))) {
						b_sub = (BNode) o;
					}
				}
				if (b_sub == null) {
					b_sub = new BNode(sub);
					data.addTriple(b_sub, trp.getPredicate(), trp.getObject());
					return true;
				} else {
					Triple _se2 = new Triple(b_sub, trp.getPredicate(), trp
							.getObject());
					if (!triplets.contains(_se2)) {
						triplets.add(_se2);
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean se1(Graph data) {
		List<Triple> triplets = data.getTriples();
		for (Triple trp : triplets) {
			RDFObject obj = trp.getObject();

			if ((obj instanceof BNode && ((BNode) obj).parent != null)) {
				continue;
			} else {
				BNode b_obj = null;
				for (Triple trp2 : triplets) {
					RDFObject s = trp2.getSubject();

					if ((s instanceof BNode) && (((BNode) s).parent != null)
							&& (((BNode) s).parent.equals(obj))) {
						b_obj = (BNode) s;
					}

					RDFObject o = trp2.getObject();

					if ((o instanceof BNode) && (((BNode) o).parent != null)
							&& (((BNode) o).parent.equals(obj))) {
						b_obj = (BNode) o;
					}
				}
				if (b_obj == null) {
					b_obj = new BNode(obj);
					data.addTriple(trp.getSubject(), trp.getPredicate(), b_obj);
					return true;
				} else {
					Triple _se1 = new Triple(trp.getSubject(), trp.getPredicate(), b_obj);
					if (!triplets.contains(_se1)) {
						triplets.add(_se1);
						return true;
					}
				}
			}
		}
		return false;
	}
}
