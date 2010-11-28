package unibz.it.edu.terms;

import unibz.it.edu.rdfElements.Uri;


public class Rdf {
	public static final String ns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	
	public static final Uri about = new Uri(ns + "about");
	public static final Uri resource = new Uri(ns + "resource");
	public static final Uri XMLLiteral = new Uri(ns + "XMLLiteral");
	public static final Uri Bag = new Uri(ns + "Bag");
	public static final Uri type = new Uri(ns + "type");
	public static final Uri li = new Uri(ns + "li");
	public static final Uri first = new Uri(ns + "first");
	public static final Uri rest = new Uri(ns + "rest");
	public static final Uri nil = new Uri(ns + "nil");
	
	public static final Uri subject = new Uri(ns + "subject");
	public static final Uri predicate = new Uri(ns + "predicate");
	public static final Uri object = new Uri(ns + "object");
	public static final Uri value = new Uri(ns+"value");
	public static final Uri Property = new Uri(ns+"Property");
	public static final Uri List = new Uri(ns+"List");
	
	public static final Uri Statement = new Uri(ns + "Statement");
	public static final Uri Alt = new Uri(ns + "Alt");
	public static final Uri Seq = new Uri(ns + "Seq");
	
 

}
