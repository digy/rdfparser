package unibz.it.edu.rdfElements;

public class BNode extends RDFObject {
	public static int bnode_counter = 1;
	private int id;
	
	public BNode() {
		super("");
		id = bnode_counter;
		++bnode_counter;
		System.out.println("Creating BNODE");
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public String toString() {
		return Integer.toString(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (this.getClass() != obj.getClass())
			return false;
		
		return true;	
	}
}
