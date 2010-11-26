package unibz.it.edu;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.Tripel;
import unibz.it.edu.rdfElements.Uri;

public class TestRDFSExpander {

	private static RDFGraphData test_data;
	private static final String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	private final static String rdfsns = "http://www.w3.org/2000/01/rdf-schema#";

	@BeforeClass
	public static void setup() {
		test_data = new RDFGraphData();
	}

	@Test
	public void test_impicitResources() {
		Graph right_data = new Graph(new LinkedList<Tripel>(test_data.basic1));
		RDFSExpander exp = new RDFSExpander();
		exp.expand(right_data);

		Tripel _res1 = new Tripel(new Uri("http://semantic-web-book.org/uri"),
				new Uri(rdfns + "rdf:type"), new Uri(rdfsns + "rdfs:Resource"));

		Tripel _res2 = new Tripel(new Uri("http://crcpress.com/uri"), new Uri(
				rdfns + "rdf:type"), new Uri(rdfsns + "rdfs:Resource"));

		assertEquals(true, right_data.getTriplets().contains(_res1));
		assertEquals(true, right_data.getTriplets().contains(_res2));

	}

	@Test
	public void test_domain() {
		Graph right_data = new Graph(new LinkedList<Tripel>(test_data.basic1));
		right_data.addTriplet_uri("http://www.example.org/publishedBy", rdfsns
				+ "rdfs:domain", "ex:book");
		RDFSExpander exp = new RDFSExpander();
		exp.expand(right_data);

		Tripel _type = new Tripel(new Uri("http://semantic-web-book.org/uri"),
				new Uri(rdfns + "rdf:type"), new Uri("ex:book"));

		assertEquals(true, right_data.getTriplets().contains(_type));
	}

	@Test
	public void test_range() {
		Graph right_data = new Graph(new LinkedList<Tripel>(test_data.basic1));
		right_data.addTriplet_uri("http://www.example.org/publishedBy", rdfsns
				+ "rdfs:range", "ex:publisher");
		RDFSExpander exp = new RDFSExpander();
		exp.expand(right_data);

		Tripel _type = new Tripel(new Uri("http://crcpress.com/uri"), new Uri(
				rdfns + "rdf:type"), new Uri("ex:publisher"));

		assertEquals(true, right_data.getTriplets().contains(_type));
	}

	@Test
	public void test_subprop() {
		Graph right_data = new Graph(new LinkedList<Tripel>(test_data.basic1));
		right_data.addTriplet_uri("http://www.example.org/publishedBy", rdfsns
				+ "rdfs:subPropertyOf", "ex:owned");
		RDFSExpander exp = new RDFSExpander();
		exp.expand(right_data);
		Tripel _subProp = new Tripel(
				new Uri("http://semantic-web-book.org/uri"),
				new Uri("ex:owned"), new Uri("http://crcpress.com/uri"));

		assertEquals(true, right_data.getTriplets().contains(_subProp));
	}

}
