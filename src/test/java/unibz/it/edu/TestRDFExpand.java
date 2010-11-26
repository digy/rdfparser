package unibz.it.edu;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.Tripel;
import unibz.it.edu.rdfElements.Uri;

public class TestRDFExpand {

	private static RDFGraphData test_data;
	private static final String rdfns = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

	@BeforeClass
	public static void setup() {
		test_data = new RDFGraphData();
	}

	@Test
	public void test_implicitProperties() {

		Graph right_data = new Graph(test_data.basic1);
		RDFExpander exp = new RDFExpander();
		exp.expand(right_data);
		Tripel _prop = new Tripel(
				new Uri("http://www.example.org/publishedBy"), new Uri("type",
						rdfns), new Uri("Property", rdfns));

		assertEquals(true, right_data.getTriplets().contains(_prop));

	}

}
