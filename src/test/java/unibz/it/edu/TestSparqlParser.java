package unibz.it.edu;

import java.io.IOException;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

import unibz.it.edu.sparql.QueryParser;
public class TestSparqlParser {
	
	@Test
	public void test_simple() throws IOException {
		QueryParser q = new QueryParser("sparql_parsing/test001.rq");
		String expected = Utils.readFile("sparql_parsing/test001.sql");
		assertEquals(expected, q.build_sql());
	}
	
	@Test
	public void test_samevar() throws IOException {
		QueryParser q = new QueryParser("sparql_parsing/test002.rq");
		String expected = Utils.readFile("sparql_parsing/test002.sql");
		assertEquals(expected, q.build_sql());
		
	}
	
	@Test
	public void test_select_star() throws IOException {
		QueryParser q = new QueryParser("sparql_parsing/test003.rq");
		String expected = Utils.readFile("sparql_parsing/test003.sql");
		assertEquals(expected, q.build_sql());
	}
	
	

}
