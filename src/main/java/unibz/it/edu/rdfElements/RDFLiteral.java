package unibz.it.edu.rdfElements;


public class RDFLiteral extends RDFObject {

	public RDFLiteral(String value) {
		super(value);
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (this.getClass() != obj.getClass())
			return false;

		final RDFLiteral other = (RDFLiteral) obj;
		return this.value.equals(other.value);
	}

}
