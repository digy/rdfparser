package unibz.it.edu;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import unibz.it.edu.parsers.RDFXMLParser;
import unibz.it.edu.parsers.TurtleParser;
import unibz.it.edu.rdfElements.BNode;
import unibz.it.edu.rdfElements.Graph;

public class W3C_Tester {

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
	
	public String[] get_fnames(String dir) {
		
	}
	

	public void test_parse(String dirname) throws IOException {
		
		File dir = new File("w3c_tests/" + dirname);
		
		File [] files = dir.listFiles(new FilenameFilter() {
		    @Override
		    public boolean accept(File dir, String name) {
		        return (name.endsWith(".rdf") && name.startsWith("test"));
		    }
		});
		
		if (files == null) {
			return;
		}

		for (File in_file : files) {
			
			String result_name = in_file.getAbsolutePath().split("\\.")[0] + ".nt";
			String result_data = readFile(result_name);
			RDFXMLParser parser = new RDFXMLParser();
			Graph parser_data = parser.decode(in_file);
			String parser_result = TurtleParser.simpleEncode(parser_data)
					.toString();
			System.out.println(in_file.getName() + "##" + parser_result + "###" + result_data);
			assertEquals(result_data, parser_result);
			BNode.bnode_counter = 1;
		}

	}

	/**
	 * Strip comments from result files
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static String readFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(new File(path));
		DataInputStream in = new DataInputStream(stream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		StringBuilder rv = new StringBuilder();
		while ((strLine = br.readLine()) != null) {
			if (!strLine.startsWith("#") && !strLine.trim().equals("")) {
				rv.append(strLine);
				rv.append("\n");
			}
		}
		return rv.toString();
	}

}
