package unibz.it.edu;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.BeforeClass;
import org.junit.Test;

import unibz.it.edu.parsers.RDFXMLParser;
import unibz.it.edu.parsers.TurtleParser;
import unibz.it.edu.rdfElements.Graph;

public class TestRDFParse {

	private static RDFGraphData test_data;

	@BeforeClass
	public static void setup() {
		test_data = new RDFGraphData();
	}

	@Test
	public void test_simple_triplet() {
		RDFXMLParser rdf = new RDFXMLParser();
		Graph get_data = rdf.decode(new File("basic1_2.xml"));

		assertEquals(get_data.getTriplets(), test_data.basic1);
	}

	@Test
	public void test_text_literal() {
		RDFXMLParser rdf = new RDFXMLParser();
		Graph get_data = rdf.decode(new File("basic2_1.xml"));

		assertEquals(get_data.getTriplets(), test_data.basic2);
	}

	@Test
	public void test_doctype_entity() {
		RDFXMLParser rdf = new RDFXMLParser();
		Graph get_data = rdf.decode(new File("basic2_2.xml"));

		assertEquals(get_data.getTriplets(), test_data.basic2);
	}

	@Test
	public void test_nested_triplets() {
		RDFXMLParser rdf = new RDFXMLParser();
		Graph get_data = rdf.decode(new File("basic3_1.xml"));

		assertEquals(get_data.getTriplets(), test_data.basic3);
	}

	@Test
	public void test_inner_values() throws URISyntaxException {
		RDFXMLParser rdf = new RDFXMLParser();
		Graph get_data = rdf.decode(new File("basic3_2.xml"));

		assertEquals(get_data.getTriplets(), test_data.basic3);
	}

	@Test
	public void test_thaiDish() throws URISyntaxException {
		RDFXMLParser rdf = new RDFXMLParser();
		Graph get_data = rdf.decode(new File("vegThaiCurry.xml"));

		assertEquals(test_data.thai, get_data.getTriplets());
	}

	@Test
	public void test_blank_1() throws URISyntaxException {
		RDFXMLParser rdf = new RDFXMLParser();
		Graph get_data = rdf.decode(new File("blank_1.xml"));

		System.out.println(TurtleParser.encode(get_data).toString());

		assertEquals(test_data.blank_1, get_data.getTriplets());
	}

}
