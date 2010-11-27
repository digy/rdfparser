package unibz.it.edu.rdfElements;

public class Literal extends RDFObject {
	private Uri datatype;
	private String lang;

	public Literal(String value) {
		super(value);
	}
	
	
	public void setDatatype(Uri value) {
		this.datatype = value;
	}
	public Uri getDatatype() {
		return this.datatype;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getLang() {
		return this.lang;
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
