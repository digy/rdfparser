package unibz.it.edu.rdfElements;

public class RDFUri extends RDFObject {
	private String namespace;

	public RDFUri(String value) {
		super(value);
		this.namespace = "";
	}
	
	public RDFUri(String value, String namespace) {
		super(value);
		this.namespace = namespace;
	}
	
	public String getNamespace() {
		return this.namespace;
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

		final RDFUri other = (RDFUri) obj;
		return this.toString().equals(other.toString());
		
	}

}
