package unibz.it.edu.terms;

import unibz.it.edu.rdfElements.Uri;

public class Rdfs {
	private final static String ns = "http://www.w3.org/2000/01/rdf-schema#";
	
	public static final Uri domain = new Uri(ns + "domain");
	public static final Uri Resource = new Uri(ns + "Resource");
	public static final Uri range = new Uri(ns + "range");
	public static final Uri subPropertyOf = new Uri (ns + "subPropertyOf");
	public static final Uri subClassOf = new Uri(ns + "subClassOf");
	public static final Uri Class = new Uri(ns + "Class");
	public static final Uri member = new Uri(ns + "member");
	public static final Uri seeAlso	= new Uri(ns + "seeAlso");
	public static final Uri isDefinedBy = new Uri(ns + "isDefinedBy");
	public static final Uri comment = new Uri(ns + "comment");
	public static final Uri label = new Uri(ns + "label");
	public static final Uri Literal = new Uri(ns + "Literal");
	public static final Uri ContainerMembershipProperty = new Uri(ns + "ContainerMembershipProperty");
	public static final Uri Container = new Uri(ns + "Container");
}
