package unibz.it.edu.rdfElements;

public abstract class RDFObject {

	protected String value;

	public RDFObject(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
