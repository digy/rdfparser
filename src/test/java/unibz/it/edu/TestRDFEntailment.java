package unibz.it.edu;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import unibz.it.edu.entailment.RDFEntailment;
import unibz.it.edu.parsers.RDFXMLParser;
import unibz.it.edu.parsers.TurtleParser;
import unibz.it.edu.rdfElements.BNode;
import unibz.it.edu.rdfElements.Graph;

public class TestRDFEntailment {
	
	public void test_entailment(String dirname) throws IOException {
		String[][] files = Utils.get_fnames(dirname);

		for (int i = 0; i < files.length; ++i) {
			String result_data = Utils.readFile(files[i][1]);
			RDFXMLParser parser = new RDFXMLParser();
			Graph parser_data = parser.decode(new File(files[i][0]));
			RDFEntailment.expand(parser_data);
			
			String parser_result = TurtleParser.simpleEncode(parser_data)
					.toString();
			assertEquals(result_data, parser_result);
			BNode.bnode_counter = 1;
		}
	}
	
	@Test
	public void test_rdf_entailment() throws IOException {
		test_entailment("rdf_entailment");
	}

}
