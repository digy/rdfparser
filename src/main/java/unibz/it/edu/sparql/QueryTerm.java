package unibz.it.edu.sparql;

public class QueryTerm {
	
	private String value;
	
	public QueryTerm(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString(){
		return this.value;
	}
}
