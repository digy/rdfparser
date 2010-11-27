package unibz.it.edu.rdfElements;

public class Uri extends RDFObject {

	public Uri(String value) {
		super(value);
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

		final Uri other = (Uri) obj;
		return this.value.equals(other.value);

	}

}
