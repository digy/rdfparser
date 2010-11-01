package unibz.it.edu;


public class RDFText extends RDFObject {

	public RDFText(String value) {
		super(value);
	}
	
	@Override
	public String toString() {
		return String.format("\"%s\"", this.value);
	}
	

}
