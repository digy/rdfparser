package unibz.it.edu;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import unibz.it.edu.db.DBManager;
import unibz.it.edu.entailment.SimpleEntailemnt;
import unibz.it.edu.parsers.RDFXMLParser;
import unibz.it.edu.rdfElements.Graph;


public class TestDBExport {
	
	private DBManager db;
	
	@Before
	public void setup() throws IOException {
		db = new DBManager();
	}

	@Test
	public void test_tables_create() throws IOException, SQLException {
		db.dropDB();
		db.prepare_table();
	}
	
	@Test
	public void test_data_dump() throws IOException, SQLException {
		RDFXMLParser parser = new RDFXMLParser();
		Graph data = parser.decode(new File("simple_entailment/test001.rdf"));
		SimpleEntailemnt.expand(data);
		db.dumpData(data);
	}
	
}
