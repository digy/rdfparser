package unibz.it.edu;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import unibz.it.edu.parsers.RDFXMLParser;
import unibz.it.edu.parsers.TurtleParser;
import unibz.it.edu.rdfElements.BNode;
import unibz.it.edu.rdfElements.Graph;

public class TestW3C {

	@Test
	public void test_amp_in_url() throws IOException {
		test_parse("amp-in-url");
	}

	@Test
	public void test_datatypes() throws IOException {
		test_parse("datatypes");
	}

	@Test
	public void test_rdf_xmllang() throws IOException {
		test_parse("rdfms-xmllang");
	}

	@Test
	public void test_xml_attrs() throws IOException {
		test_parse("unrecognised-xml-attributes");
	}

	@Test
	public void test_xml_canon() throws IOException {
		test_parse("xml-canon");
	}

	@Test
	public void test_rdf_containers_syntax_vs_schema() throws IOException {
		test_parse("rdf-containers-syntax-vs-schema");
	}

	@Test
	public void test_rdfms_identity_anon_resources() throws IOException {
		test_parse("rdfms-identity-anon-resources");
	}

	@Test
	public void test_rdfms_para196() throws IOException {
		test_parse("rdfms-para196");
	}

	@Test
	public void test_rdfms_rdf_names_use() throws IOException {
		test_parse("rdfms-rdf-names-use");
	}

	@Test
	public void test_rdfms_seq_representation() throws IOException {
		test_parse("rdfms-seq-representation");
	}

	@Test
	public void test_rdfs_domain_and_range() throws IOException {
		test_parse("rdfs-domain-and-range");
	}



	public void test_parse(String dirname) throws IOException {

		String[][] files = Utils.get_fnames("w3c_tests/" + dirname);

		for (int i = 0; i < files.length; ++i) {
			String result_data = Utils.readFile(files[i][1]);
			RDFXMLParser parser = new RDFXMLParser();
			Graph parser_data = parser.decode(new File(files[i][0]));
			String parser_result = TurtleParser.simpleEncode(parser_data)
					.toString();
			assertEquals(result_data, parser_result);
			BNode.bnode_counter = 1;
		}
	}

}
