package unibz.it.edu.rdfElements;

public class BNode extends RDFObject {
	public static int bnode_counter = 1;
	private int id;
	public RDFObject parent;
	
	public BNode() {
		super(Integer.toString(bnode_counter++));
	}
	
	public BNode(RDFObject parent) {
		super(Integer.toString(bnode_counter++));
		this.parent = parent;
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (this.getClass() != obj.getClass())
			return false;
		
		final BNode other = (BNode) obj;
		
//		if ((this.parent != null) && (other.parent != null)) {
//			return this.parent.equals(other.parent);
//		}
		return true;	
	}
}
