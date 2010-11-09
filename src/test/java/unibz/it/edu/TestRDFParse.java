package unibz.it.edu;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.BeforeClass;
import org.junit.Test;

import unibz.it.edu.parsers.RDFParser;
import unibz.it.edu.rdfElements.RDFGraph;

public class TestRDFParse {
	
	private static RDFGraphData test_data;
	
	@BeforeClass
	public static void setup() {
		test_data = new RDFGraphData();	
	}
	
	@Test
	public void test_simple_triplet() {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic1_2.xml"));
		
		assertEquals(get_data.getTriplets(), test_data.basic1);
	}
		
	@Test
	public void test_text_literal() {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic2_1.xml"));
		
		assertEquals(get_data.getTriplets(), test_data.basic2 );
	}
	@Test
	public void test_doctype_entity() {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic2_2.xml"));
		
		assertEquals(get_data.getTriplets(), test_data.basic2 );
	}
	
	@Test
	public void test_nested_triplets() {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic3_1.xml"));
		
		assertEquals(get_data.getTriplets(), test_data.basic3 );
	}
	@Test
	public void test_inner_values() throws URISyntaxException {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic3_2.xml"));
		
		assertEquals(get_data.getTriplets(), test_data.basic3 );
	}
	

}
