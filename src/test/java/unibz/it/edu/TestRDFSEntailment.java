package unibz.it.edu;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import unibz.it.edu.entailment.RDFSEntailment;
import unibz.it.edu.parsers.RDFXMLParser;
import unibz.it.edu.parsers.TurtleParser;
import unibz.it.edu.rdfElements.BNode;
import unibz.it.edu.rdfElements.Graph;

public class TestRDFSEntailment {

	public void test_entailment(String dirname) throws IOException {
		String[][] files = Utils.get_fnames(dirname);

		for (int i = 0; i < files.length; ++i) {
			String result_data = Utils.readFile(files[i][1]);
			RDFXMLParser parser = new RDFXMLParser();
			Graph parser_data = parser.decode(new File(files[i][0]));
			RDFSEntailment.expand(parser_data);
			
			String parser_result = TurtleParser.simpleEncode(parser_data)
					.toString();
			assertEquals(result_data, parser_result);
			BNode.bnode_counter = 1;
		}
	}
	
	@Test
	public void test_rdfs2() throws IOException {
		String fname = "rdfs_entailment/test_rdfs2";
		String result_data = Utils.readFile(fname+".nt");
		RDFXMLParser parser = new RDFXMLParser();
		Graph parser_data = parser.decode(new File(fname+".rdf"));
		while(RDFSEntailment.rdfs2(parser_data))
		{
		}
		String parser_result = TurtleParser.simpleEncode(parser_data).toString();
		assertEquals(result_data, parser_result);
		BNode.bnode_counter = 1;
	}
	
	@Test
	public void test_rdfs3() throws IOException {
		String fname = "rdfs_entailment/test_rdfs3";
		String result_data = Utils.readFile(fname+".nt");
		RDFXMLParser parser = new RDFXMLParser();
		Graph parser_data = parser.decode(new File(fname+".rdf"));
		while(RDFSEntailment.rdfs3(parser_data))
		{
		}
		String parser_result = TurtleParser.simpleEncode(parser_data).toString();
		assertEquals(result_data, parser_result);
		BNode.bnode_counter = 1;
	}
	
	@Test
	public void test_rdfs4() throws IOException {
		String fname = "rdfs_entailment/test_rdfs4";
		String result_data = Utils.readFile(fname+".nt");
		RDFXMLParser parser = new RDFXMLParser();
		Graph parser_data = parser.decode(new File(fname+".rdf"));
		while(RDFSEntailment.rdfs4(parser_data))
		{
		}
		String parser_result = TurtleParser.simpleEncode(parser_data).toString();
		assertEquals(result_data, parser_result);
		BNode.bnode_counter = 1;
	}
	
	@Test
	public void test_rdfs5() throws IOException {
		String fname = "rdfs_entailment/test_rdfs5";
		String result_data = Utils.readFile(fname+".nt");
		RDFXMLParser parser = new RDFXMLParser();
		Graph parser_data = parser.decode(new File(fname+".rdf"));
		while(RDFSEntailment.rdfs5(parser_data))
		{
		}
		String parser_result = TurtleParser.simpleEncode(parser_data).toString();
		assertEquals(result_data, parser_result);
		BNode.bnode_counter = 1;
	}
	
	@Test
	public void test_rdfs6() throws IOException {
		String fname = "rdfs_entailment/test_rdfs6";
		String result_data = Utils.readFile(fname+".nt");
		RDFXMLParser parser = new RDFXMLParser();
		Graph parser_data = parser.decode(new File(fname+".rdf"));
		while(RDFSEntailment.rdfs6(parser_data))
		{
		}
		String parser_result = TurtleParser.simpleEncode(parser_data).toString();
		assertEquals(result_data, parser_result);
		BNode.bnode_counter = 1;
	}
	
	@Test
	public void test_rdfs7() throws IOException {
		String fname = "rdfs_entailment/test_rdfs7";
		String result_data = Utils.readFile(fname+".nt");
		RDFXMLParser parser = new RDFXMLParser();
		Graph parser_data = parser.decode(new File(fname+".rdf"));
		while(RDFSEntailment.rdfs5(parser_data)|| RDFSEntailment.rdfs7(parser_data))
		{
		}
		String parser_result = TurtleParser.simpleEncode(parser_data).toString();
		assertEquals(result_data, parser_result);
		BNode.bnode_counter = 1;
	}
	
	@Test
	public void test_rdfs9() throws IOException {
		String fname = "rdfs_entailment/test_rdfs9";
		String result_data = Utils.readFile(fname+".nt");
		RDFXMLParser parser = new RDFXMLParser();
		Graph parser_data = parser.decode(new File(fname+".rdf"));
		while(RDFSEntailment.rdfs9(parser_data))
		{
		}
		String parser_result = TurtleParser.simpleEncode(parser_data).toString();
		assertEquals(result_data, parser_result);
		BNode.bnode_counter = 1;
	}
	
	@Test
	public void test_rdfs11() throws IOException {
		String fname = "rdfs_entailment/test_rdfs11";
		String result_data = Utils.readFile(fname+".nt");
		RDFXMLParser parser = new RDFXMLParser();
		Graph parser_data = parser.decode(new File(fname+".rdf"));
		while(RDFSEntailment.rdfs11(parser_data))
		{
		}
		String parser_result = TurtleParser.simpleEncode(parser_data).toString();
		assertEquals(result_data, parser_result);
		BNode.bnode_counter = 1;
	}
	
	@Test
	public void test_all() throws IOException {
		String fname = "rdfs_entailment/test_all";
		String result_data = Utils.readFile(fname+".nt");
		RDFXMLParser parser = new RDFXMLParser();
		Graph parser_data = parser.decode(new File(fname+".rdf"));
		RDFSEntailment.expand(parser_data);
		String parser_result = TurtleParser.simpleEncode(parser_data).toString();
		assertEquals(result_data, parser_result);
		BNode.bnode_counter = 1;
	}
	
//	@Test
//	public void test_rdf_entailment() throws IOException {
//		test_entailment("rdfs_entailment");
//	}
}
