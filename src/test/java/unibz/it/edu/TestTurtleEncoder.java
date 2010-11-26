package unibz.it.edu;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import unibz.it.edu.parsers.TurtleParser;
import unibz.it.edu.rdfElements.Graph;

public class TestTurtleEncoder {

	private static RDFGraphData test_data;
	private static String basic1;

	@BeforeClass
	public static void setup() {
		test_data = new RDFGraphData();
		basic1 = "<http://semantic-web-book.org/uri> <http://www.example.org/publishedBy> <http://crcpress.com/uri> .\n";
	}

	@Test
	public void test_simple_triplet() {
		StringBuilder result = TurtleParser.encode(new Graph(test_data.basic1));
		assertEquals(result.toString(), basic1);
	}

}
