package unibz.it.edu;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import unibz.it.edu.rdfElements.RDFGraph;
import unibz.it.edu.rdfElements.RDFLiteral;
import unibz.it.edu.rdfElements.RDFTriplet;
import unibz.it.edu.rdfElements.RDFUri;

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
		RDFGraph right_data = new RDFGraph(new LinkedList<RDFTriplet>(
				test_data.basic1));
		RDFSExpander exp = new RDFSExpander();
		exp.expand(right_data);

		RDFTriplet _res1 = new RDFTriplet(new RDFUri(
				"http://semantic-web-book.org/uri"), new RDFUri(rdfns
				+ "rdf:type"), new RDFUri(rdfsns + "rdfs:Resource"));

		RDFTriplet _res2 = new RDFTriplet(
				new RDFUri("http://crcpress.com/uri"), new RDFUri(rdfns
						+ "rdf:type"), new RDFUri(rdfsns + "rdfs:Resource"));

		assertEquals(true, right_data.getTriplets().contains(_res1));
		assertEquals(true, right_data.getTriplets().contains(_res2));

	}

	@Test
	public void test_litera() {
		RDFGraph right_data = new RDFGraph(new LinkedList<RDFTriplet>(
				test_data.basic2));
		RDFSExpander exp = new RDFSExpander();
		exp.expand(right_data);
		RDFTriplet _lit = new RDFTriplet(new RDFLiteral(
				"World Wide Web Consortium"), new RDFUri(rdfns + "rdf:type"),
				new RDFUri(rdfsns + "rdfs:Literal"));

		assertEquals(true, right_data.getTriplets().contains(_lit));
	}

	@Test
	public void test_domain() {
		RDFGraph right_data = new RDFGraph(new LinkedList<RDFTriplet>(
				test_data.basic1));
		right_data.addTriplet_uri("http://www.example.org/publishedBy", rdfsns
				+ "rdfs:domain", "ex:book");
		RDFSExpander exp = new RDFSExpander();
		exp.expand(right_data);

		RDFTriplet _type = new RDFTriplet(new RDFUri(
				"http://semantic-web-book.org/uri"), new RDFUri(rdfns
				+ "rdf:type"), new RDFUri("ex:book"));

		assertEquals(true, right_data.getTriplets().contains(_type));
	}

	@Test
	public void test_range() {
		RDFGraph right_data = new RDFGraph(new LinkedList<RDFTriplet>(
				test_data.basic1));
		right_data.addTriplet_uri("http://www.example.org/publishedBy", rdfsns
				+ "rdfs:range", "ex:publisher");
		RDFSExpander exp = new RDFSExpander();
		exp.expand(right_data);

		RDFTriplet _type = new RDFTriplet(
				new RDFUri("http://crcpress.com/uri"), new RDFUri(rdfns
						+ "rdf:type"), new RDFUri("ex:publisher"));

		assertEquals(true, right_data.getTriplets().contains(_type));
	}

	@Test
	public void test_subprop() {
		RDFGraph right_data = new RDFGraph(new LinkedList<RDFTriplet>(
				test_data.basic1));
		right_data.addTriplet_uri("http://www.example.org/publishedBy", rdfsns
				+ "rdfs:subPropertyOf", "ex:owned");
		RDFSExpander exp = new RDFSExpander();
		exp.expand(right_data);
		RDFTriplet _subProp = new RDFTriplet(new RDFUri(
				"http://semantic-web-book.org/uri"), new RDFUri("ex:owned"),
				new RDFUri("http://crcpress.com/uri"));

		assertEquals(true, right_data.getTriplets().contains(_subProp));
	}

}
