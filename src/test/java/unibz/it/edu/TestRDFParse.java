package unibz.it.edu;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestRDFParse {
	
	private static List<RDFTriplet> basic1;
	private static List<RDFTriplet> basic2;
	private static List<RDFTriplet> basic3;
	
	@BeforeClass
	public static void setup() {
		basic1 = new LinkedList<RDFTriplet>();	
		basic1.add(new RDFTriplet(new RDFObject("http://semantic-web-book.org/uri"),
				  new RDFObject("http://www.example.org/publishedBy"),
				  new RDFObject("http://crcpress.com/uri")));
		
		basic2 = new LinkedList<RDFTriplet>();
		basic2.add(new RDFTriplet(new RDFObject("http://www.w3.org/"),
				  new RDFObject("http://www.w3.org/title"),
				  new RDFObject("World Wide Web Consortium")));
		basic3 = new LinkedList<RDFTriplet>();
		
		basic3.add(new RDFTriplet(
					new RDFObject("http://semantic-web-book.org/uri"),
					new RDFObject("http://www.example.org/title"),
					new RDFObject("Foundations of Semantic Web Technologies")));
		basic3.add(new RDFTriplet(
					new RDFObject("http://semantic-web-book.org/uri"),
					new RDFObject("http://www.example.org/publishedBy"),
					new RDFObject("http://crcpress.com/uri")));
		basic3.add(new RDFTriplet(
					new RDFObject("http://crcpress.com/uri"),
					new RDFObject("http://www.example.org/name"),
					new RDFObject("CRC Press")));
		
	}
	
	@Test
	public void test_simple_triplet() {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic1_2.xml"));
		
		assertEquals(get_data.getTriplets(), basic1);
	}
	
	
	@Test
	public void test_xml_base() {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic1_1.xml"));
		
		assertEquals(get_data.getTriplets(), basic1 );
	}
	
	@Test
	public void test_text_literal() {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic2_1.xml"));
		
		assertEquals(get_data.getTriplets(), basic2 );
	}
	@Test
	public void test_doctype_entity() {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic2_2.xml"));
		
		assertEquals(get_data.getTriplets(), basic2 );
	}
	
	@Test
	public void test_nested_triplets() {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic3_1.xml"));
		
		assertEquals(get_data.getTriplets(), basic3 );
	}
	@Test
	public void test_inner_values() throws URISyntaxException {
		RDFParser rdf = new RDFParser();
		RDFGraph get_data = rdf.decode(new File("basic3_2.xml"));
		
		assertEquals(get_data.getTriplets(), basic3 );
	}
	

}
