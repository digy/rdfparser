package unibz.it.edu.rdfElements;

public class Tripel {

	private final Uri subject;
	private final Uri predicate;
	private final RDFObject object;

	public Tripel(RDFObject s, RDFObject p, RDFObject o) {
		subject = (Uri) s;
		predicate = (Uri) p;
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

		final Tripel other = (Tripel) obj;

		return ((subject.equals(other.subject))
				&& (predicate.equals(other.predicate)) && (object
				.equals(other.object)));
	}

	public Uri getSubject() {
		return subject;
	}

	public Uri getPredicate() {
		return predicate;
	}

	public RDFObject getObject() {
		return object;
	}

}
