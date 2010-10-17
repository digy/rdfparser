package unibz.it.edu;

public class RDFObject {

	private String value;

	public RDFObject(String value) {
		this.value = value;
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

		final RDFObject other = (RDFObject) obj;
		return this.value.equals(other.value);
	}
}
