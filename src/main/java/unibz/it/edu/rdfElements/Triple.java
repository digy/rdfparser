package unibz.it.edu.rdfElements;

public class Triple {

	private final RDFObject subject;
	private final RDFObject predicate;
	private final RDFObject object;

	public Triple(RDFObject s, RDFObject p, RDFObject o) {
		subject =  s;
		predicate = p;
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

		final Triple other = (Triple) obj;

		return ((subject.equals(other.subject))
				&& (predicate.equals(other.predicate)) && (object
				.equals(other.object)));
	}

	public RDFObject getSubject() {
		return subject;
	}

	public RDFObject getPredicate() {
		return predicate;
	}

	public RDFObject getObject() {
		return object;
	}

}
