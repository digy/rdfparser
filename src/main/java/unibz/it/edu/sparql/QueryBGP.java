package unibz.it.edu.sparql;


public class QueryBGP {


	private QueryTerm term1;
	private QueryTerm pred;
	private QueryTerm term2;
	
	public QueryBGP(QueryTerm term1, QueryTerm pred, QueryTerm term2) {
		this.term1 = term1;
		this.pred = pred;
		this.term2 = term2;
	}
	
	public QueryTerm getTerm1() {
		return term1;
	}

	public QueryTerm getPred() {
		return pred;
	}

	public QueryTerm getTerm2() {
		return term2;
	}
	
	public String toString() {
		return String.format("(%s;%s;%s)", term1, pred, term2);
	}
	

}
