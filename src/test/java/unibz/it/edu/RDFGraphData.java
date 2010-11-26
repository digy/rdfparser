package unibz.it.edu;

import java.util.LinkedList;
import java.util.List;

import unibz.it.edu.rdfElements.Literal;
import unibz.it.edu.rdfElements.Tripel;
import unibz.it.edu.rdfElements.Uri;

public class RDFGraphData {

	public List<Tripel> basic1;
	public List<Tripel> basic2;
	public List<Tripel> basic3;
	public List<Tripel> thai;
	public List<Tripel> blank_1;

	public final static String rdfsns = "http://www.w3.org/2000/01/rdf-schema#";
	public final static String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public final static String ex_ns = "http://www.example.org/";

	public RDFGraphData() {
		String book_ns = "http://semantic-web-book.org/";

		basic1 = new LinkedList<Tripel>();
		basic1.add(new Tripel(new Uri("uri", book_ns), new Uri("publishedBy",
				ex_ns), new Uri("http://crcpress.com/uri")));

		basic2 = new LinkedList<Tripel>();
		basic2.add(new Tripel(new Uri("http://www.w3.org/"), new Uri(
				"http://www.w3.org/title"), new Literal(
				"World Wide Web Consortium")));

		basic3 = new LinkedList<Tripel>();
		basic3.add(new Tripel(new Uri("uri", book_ns), new Uri("title", ex_ns),
				new Literal("Foundations of Semantic Web Technologies")));
		basic3.add(new Tripel(new Uri("uri", book_ns), new Uri("publishedBy",
				ex_ns), new Uri("http://crcpress.com/uri")));
		basic3.add(new Tripel(new Uri("http://crcpress.com/uri"), new Uri(
				"http://www.example.org/name"), new Literal("CRC Press")));

		thai = new LinkedList<Tripel>();
		thai.add(new Tripel(new Uri("vegetableThaiCurry", ex_ns), new Uri(
				"thaiDishBasedOn", ex_ns), new Uri("coconutMilk", ex_ns)));
		thai.add(new Tripel(new Uri("sebastin", ex_ns), new Uri("type", rdfns),
				new Uri("AllergicToNuts", ex_ns)));
		thai.add(new Tripel(new Uri("sebastin", ex_ns), new Uri("eats", ex_ns),
				new Uri("vegetableThaiCurry", ex_ns)));

		thai.add(new Tripel(new Uri("AllergicToNuts", ex_ns), new Uri(
				"subClassOf", rdfsns), new Uri("Pitiable", ex_ns)));

		thai.add(new Tripel(new Uri("thaiDishBasedOn", ex_ns), new Uri(
				"domain", rdfsns), new Uri("Thai", ex_ns)));
		thai.add(new Tripel(new Uri("thaiDishBasedOn", ex_ns), new Uri("range",
				rdfsns), new Uri("Nutty", ex_ns)));
		thai.add(new Tripel(new Uri("thaiDishBasedOn", ex_ns), new Uri(
				"subPropertyOf", rdfsns), new Uri("hasIngredient", ex_ns)));
		thai.add(new Tripel(new Uri("hasIngredient", ex_ns), new Uri("type",
				rdfns), new Uri("ContainerMembershipProperty", rdfsns)));

		// blank_1 = new LinkedList<RDFTriplet>();
	}
}
