package unibz.it.edu.rdfElements;

public class Uri extends RDFObject {
	private String namespace;

	public Uri(String value) {
		super(value);
		this.namespace = "";
	}

	public Uri(String value, String namespace) {
		super(value);
		this.namespace = namespace;
	}

	public String getNamespace() {
		return this.namespace;
	}

	@Override
	public int hashCode() {
		return value.hashCode() + namespace.hashCode();
	}

	@Override
	public String toString() {
		return namespace + value;
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
		return this.toString().equals(other.toString());

	}

}
