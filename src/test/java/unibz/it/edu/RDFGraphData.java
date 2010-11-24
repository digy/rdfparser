package unibz.it.edu;

import java.util.LinkedList;
import java.util.List;

import unibz.it.edu.rdfElements.RDFLiteral;
import unibz.it.edu.rdfElements.RDFTriplet;
import unibz.it.edu.rdfElements.RDFUri;

public class RDFGraphData {

	public List<RDFTriplet> basic1;
	public List<RDFTriplet> basic2;
	public List<RDFTriplet> basic3;
	
	public List<RDFTriplet> thai;
	
	public final static String rdfsns = "http://www.w3.org/2000/01/rdf-schema#";
	public final static String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public final static String ex_ns = "http://www.example.org/";



	public RDFGraphData() {
		String book_ns = "http://semantic-web-book.org/";
		
		
		basic1 = new LinkedList<RDFTriplet>();
		basic1.add(new RDFTriplet(
									new RDFUri("uri", book_ns),
									new RDFUri("publishedBy", ex_ns),
									new RDFUri("http://crcpress.com/uri")));

		basic2 = new LinkedList<RDFTriplet>();
		basic2.add(new RDFTriplet(
				new RDFUri("http://www.w3.org/"),
				new RDFUri("http://www.w3.org/title"),
				new RDFLiteral("World Wide Web Consortium")));
		
		basic3 = new LinkedList<RDFTriplet>();
		basic3.add(new RDFTriplet(
				new RDFUri("uri", book_ns), 
				new RDFUri("title", ex_ns),
				new RDFLiteral("Foundations of Semantic Web Technologies")));
		basic3.add(new RDFTriplet(
				new RDFUri("uri", book_ns),
				new RDFUri("publishedBy", ex_ns),
				new RDFUri("http://crcpress.com/uri")));
		basic3.add(new RDFTriplet(new RDFUri("http://crcpress.com/uri"),
				new RDFUri("http://www.example.org/name"), new RDFLiteral(
						"CRC Press")));
		
		thai = new LinkedList<RDFTriplet>();
		thai.add(new RDFTriplet(
				new RDFUri("vegetableThaiCurry", ex_ns),
				new RDFUri("thaiDishBasedOn", ex_ns),
				new RDFUri("coconutMilk", ex_ns)));
		thai.add(new RDFTriplet(
				new RDFUri("sebastin", ex_ns),
				new RDFUri("type", rdfns),
				new RDFUri("AllergicToNuts", ex_ns)));
		thai.add(new RDFTriplet(
				new RDFUri("sebastin", ex_ns),
				new RDFUri("eats", ex_ns),
				new RDFUri("vegetableThaiCurry", ex_ns)));
		
		thai.add(new RDFTriplet(
				new RDFUri("AllergicToNuts", ex_ns),
				new RDFUri("subClassOf", rdfsns),
				new RDFUri("Pitiable", ex_ns)));
		
		thai.add(new RDFTriplet(
				new RDFUri("thaiDishBasedOn", ex_ns),
				new RDFUri("domain", rdfsns),
				new RDFUri("Thai", ex_ns)));
		thai.add(new RDFTriplet(
				new RDFUri("thaiDishBasedOn", ex_ns),
				new RDFUri("range", rdfsns),
				new RDFUri("Nutty", ex_ns)));
		thai.add(new RDFTriplet(
				new RDFUri("thaiDishBasedOn", ex_ns),
				new RDFUri("subPropertyOf", rdfsns),
				new RDFUri("hasIngredient", ex_ns)));
		thai.add(new RDFTriplet(
				new RDFUri("hasIngredient", ex_ns),
				new RDFUri("type", rdfns),
				new RDFUri("ContainerMembershipProperty", rdfsns)));		
	}
}
