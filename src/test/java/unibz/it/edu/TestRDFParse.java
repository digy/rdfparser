package unibz.it.edu;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestRDFParse {
	
	private static LinkedList<RDFTriplet> basic1;
	
	@BeforeClass
	public static void setup() throws URISyntaxException{
		basic1 = new LinkedList<RDFTriplet>();	
		basic1.add(new RDFTriplet(new RDFObject(new URI("http://semantic-web-book.org/uri")),
				  new RDFObject(new URI("http://www.example.org/publishedBy")),
				  new RDFObject(new URI("http://crcpress.com/uri"))));
		
	}
	
	@Test
	public void test_simple_triplet() throws URISyntaxException {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic1_2.xml"));
		
		assertEquals(get_data.getTriplets(), basic1 );
	}
	
	@Test
	public void test_xml_base() throws URISyntaxException {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic1_1.xml"));
		
		assertEquals(get_data.getTriplets(), basic1 );
	}
	
	@Test
	public void test_text_literal() throws URISyntaxException {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic2_1.xml"));
		
		assertEquals(get_data.getTriplets(), basic1 );
	}
	@Test
	public void test_doctype_entity() throws URISyntaxException {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic2_2.xml"));
		
		assertEquals(get_data.getTriplets(), basic1 );
	}
	
	@Test
	public void test_nested_triplets() throws URISyntaxException {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic3_1.xml"));
		
		assertEquals(get_data.getTriplets(), basic1 );
	}
	@Test
	public void test_inner_values() throws URISyntaxException {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic3_2.xml"));
		
		assertEquals(get_data.getTriplets(), basic1 );
	}
	

}
