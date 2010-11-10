package unibz.it.edu.rdfElements;


public class RDFTriplet {


	private final RDFUri subject;
	private final RDFUri predicate;
	private final RDFObject object;

	public RDFTriplet(RDFObject s, RDFObject p, RDFObject o) {
		subject = (RDFUri) s;
		predicate = (RDFUri) p;
		object = o;
	}

	public String toString() {
		return String.format("(%s;%s;%s)", subject, predicate, object);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (this.getClass() != obj.getClass())
			return false;

		final RDFTriplet other = (RDFTriplet) obj;
		
		return ((subject.equals(other.subject)) && (predicate.equals(other.predicate)) && (object.equals(other.object)));
	}
	public RDFUri getSubject() {
		return subject;
	}

	public RDFUri getPredicate() {
		return predicate;
	}

	public RDFObject getObject() {
		return object;
	}

}
