package unibz.it.edu.rdfElements;

public class Literal extends RDFObject {

	public Literal(String value) {
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

		final Literal other = (Literal) obj;
		return this.value.equals(other.value);
	}

}
