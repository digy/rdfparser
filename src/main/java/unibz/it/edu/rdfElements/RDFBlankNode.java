package unibz.it.edu.rdfElements;

public class RDFBlankNode extends RDFObject {
	
	public RDFBlankNode() {
		super("");
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
		
		return true;
		
	}


}