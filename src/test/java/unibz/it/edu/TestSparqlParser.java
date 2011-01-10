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
	
	@Test
	public void test_join_var() throws IOException {
		QueryParser q = new QueryParser("sparql_parsing/test004.rq");
		String expected = Utils.readFile("sparql_parsing/test004.sql");
		assertEquals(expected, q.build_sql());
	}
	
	@Test
	public void test_multi_criteria_sub() throws IOException {
		QueryParser q = new QueryParser("sparql_parsing/test005.rq");
		String expected = Utils.readFile("sparql_parsing/test005.sql");
		assertEquals(expected, q.build_sql());
	}
	
	@Test
	public void test_multi_criteria_pred() throws IOException {
		QueryParser q = new QueryParser("sparql_parsing/test006.rq");
		String expected = Utils.readFile("sparql_parsing/test006.sql");
		assertEquals(expected, q.build_sql());
	}
	
	@Test
	public void test_multi_criteria_obj() throws IOException {
		QueryParser q = new QueryParser("sparql_parsing/test007.rq");
		String expected = Utils.readFile("sparql_parsing/test007.sql");
		assertEquals(expected, q.build_sql());
	}
	
	@Test
	public void test_mixded_var() throws IOException {
		QueryParser q = new QueryParser("sparql_parsing/test008.rq");
		String expected = Utils.readFile("sparql_parsing/test008.sql");
		assertEquals(expected, q.build_sql());
	}
	
	@Test
	public void test_multi_joins() throws IOException {
		QueryParser q = new QueryParser("sparql_parsing/test009.rq");
		String expected = Utils.readFile("sparql_parsing/test009.sql");
		assertEquals(expected, q.build_sql());
	}


}
