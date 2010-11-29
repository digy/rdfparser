package unibz.it.edu.sparql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import unibz.it.edu.Utils;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.Node_Literal;
import com.hp.hpl.jena.graph.Node_URI;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.sparql.core.BasicPattern;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.syntax.Element;
import com.hp.hpl.jena.sparql.syntax.ElementGroup;
import com.hp.hpl.jena.sparql.syntax.ElementTriplesBlock;

public class QueryParser {

	private List<QueryVar> headVariabls;
	private List<QueryBGP> bgps;

	public QueryParser(String filename) throws IOException {
		String content = Utils.readFile(filename);
		Query query = QueryFactory.create(content);

		headVariabls = new ArrayList<QueryVar>();

		List sel_vars = query.getResultVars();
		for (int i = 0; i < sel_vars.size(); i++) {
			Object var = sel_vars.get(i);
			headVariabls.add(new QueryVar(var.toString()));
		}

		bgps = new ArrayList<QueryBGP>();

		Element pattern = query.getQueryPattern();
		ElementGroup group = (ElementGroup) pattern;
		List list = group.getElements();
		for (int k = 0; k < list.size(); k++) {

			ElementGroup current_group = null;
			ElementTriplesBlock triplesBlock = null;

			if (list.get(k) instanceof ElementGroup) {
				current_group = (ElementGroup) list.get(k);
				triplesBlock = (ElementTriplesBlock) current_group
						.getElements().get(0);
			} else if (list.get(k) instanceof ElementTriplesBlock) {
				triplesBlock = (ElementTriplesBlock) list.get(0);
			}

			BasicPattern triples = triplesBlock.getPattern();

			for (int i = 0; i < triples.size(); i++) {
				Triple triple = triples.get(i);

				Node o = triple.getObject();
				Node p = triple.getPredicate();
				Node s = triple.getSubject();

				QueryTerm term1 = null;
				QueryTerm term2 = null;
				QueryTerm pred = null;

				if (s instanceof Var) {
					Var subject = (Var) s;
					term1 = new QueryVar(subject.getName());
				} else if (s instanceof Node_Literal) {
					Node_Literal subject = (Node_Literal) s;
					term1 = new QueryVal(subject.getLiteralValue().toString());
				} else if (o instanceof Node_URI) {
					Node_URI subject = (Node_URI) s;
					term2 = new QueryVal(subject.getURI());
				}

				if (o instanceof Var) {
					Var object = (Var) o;
					term2 = new QueryVar(object.getName());
				} else if (o instanceof Node_Literal) {
					Node_Literal object = (Node_Literal) s;
					term2 = new QueryVal(object.getLiteralValue().toString());
				} else if (o instanceof Node_URI) {
					Node_URI object = (Node_URI) o;
					term2 = new QueryVal(object.getURI());
				}

				if (p instanceof Var) {
					Var predicate = (Var) p;
					pred = new QueryVar(predicate.getName());
				} else if (p instanceof Node_URI) {
					Node_URI predicate = (Node_URI) p;
					pred = new QueryVal(predicate.getURI());
				}

				bgps.add(new QueryBGP(term1, pred, term2));
			}
		}
	}

	public String build_sql() {

		StringBuilder select_clause = new StringBuilder();
		for (QueryVar var : headVariabls) {

			int counter = 1;
			for (QueryBGP query : bgps) {
				if (query.getTerm1() instanceof QueryVar
						&& query.getTerm1().getValue().equals(var.getValue())) {
					select_clause.append(String.format("bg%d.sub%s,", counter,
							query.getTerm1().toString()));
					break;
				}
				if (query.getTerm2() instanceof QueryVar
						&& query.getTerm2().getValue().equals(var.getValue())) {
					select_clause.append(String.format("bg%d.obj%s,", counter,
							query.getTerm2().toString()));
					break;
				}
				if (query.getPred() instanceof QueryVar
						&& query.getPred().getValue().equals(var.getValue())) {
					select_clause.append(String.format("bg%d.pre%s,", counter,
							query.getPred().toString()));
					break;
				}
				counter++;
			}
		}
		select_clause
				.delete(select_clause.length() - 1, select_clause.length());

		int counter = 1;
		StringBuffer from_clause = new StringBuffer();
		for (QueryBGP query : bgps) {

			StringBuilder where_clause = new StringBuilder();
			StringBuilder name_clause = new StringBuilder();

			if (query.getTerm1() instanceof QueryVal) {
				where_clause.append(String.format("subject =\'%s\' and ", query
						.getTerm1().toString()));
			} else {
				name_clause.append(String.format("subject as sub%s,", query.getTerm1().toString()));
			}

			if (query.getPred() instanceof QueryVal) {
				where_clause.append(String.format("predicate =\'%s\' and ",
						query.getPred().toString()));
			} else {
				name_clause.append(String.format("predicate as pre%s,", query.getPred().toString()));
			}

			if (query.getTerm2() instanceof QueryVal) {
				where_clause.append(String.format("object =\'%s\' and ", query
						.getPred().toString()));
			} else {
				name_clause.append(String.format("object as obj%s,", query.getTerm2().toString()));
			}

			if (where_clause.length() > 0) {
				where_clause.delete(where_clause.length() - 4, where_clause
						.length());
			}
			if (name_clause.length() > 0) {
			name_clause.delete(name_clause.length() - 1, name_clause.length());
			}

			String q = String.format(
					"(SELECT %s FROM data WHERE %s ) as bg%d, \n", name_clause,
					where_clause, counter);
			from_clause.append(q);
			counter++;
		}
		
		from_clause.delete(from_clause.length() - 3, from_clause.length());

		StringBuilder where_clause = new StringBuilder();

		counter = 1;
		for (QueryBGP query : bgps) {

			if (query.getTerm1() instanceof QueryVar) {

				QueryVar var = (QueryVar) query.getTerm1();
				int inner_counter = 1;
				for (QueryBGP inner_query : bgps) {

					if (inner_query.getTerm1() instanceof QueryVar
							&& inner_query.getTerm1().getValue().equals(
									var.getValue())) {
						where_clause.append(String.format(
								"bg%d.sub%s=bg%d.sub%s and ", inner_counter,
								inner_query.getTerm1().toString(), counter, var
										.toString()));
					}
					if (inner_query.getTerm2() instanceof QueryVar
							&& inner_query.getTerm2().getValue().equals(
									var.getValue())) {
						where_clause.append(String.format(
								"bg%d.obj%s=bg%d.sub%s and ", inner_counter,
								inner_query.getTerm2().toString(), counter, var
										.toString()));
					}
					if (inner_query.getPred() instanceof QueryVar
							&& inner_query.getPred().getValue().equals(
									var.getValue())) {
						where_clause.append(String.format(
								"bg%d.pre%s=bg%d.sub%s and ", inner_counter,
								inner_query.getPred().toString(), counter, var
										.toString()));
					}

					inner_counter++;
				}
			}

			if (query.getTerm2() instanceof QueryVar) {

				QueryVar var = (QueryVar) query.getTerm2();
				int inner_counter = 1;
				for (QueryBGP inner_query : bgps) {

					if (inner_query.getTerm1() instanceof QueryVar
							&& inner_query.getTerm1().getValue().equals(
									var.getValue())) {
						where_clause.append(String.format(
								"bg%d.sub%s=bg%d.obj%s and ", inner_counter,
								inner_query.getTerm1().toString(), counter, var
										.toString()));
					}
					if (inner_query.getTerm2() instanceof QueryVar
							&& inner_query.getTerm2().getValue().equals(
									var.getValue())) {
						where_clause.append(String.format(
								"bg%d.obj%s=bg%d.obj%s and ", inner_counter,
								inner_query.getTerm2().toString(), counter, var
										.toString()));
					}
					if (inner_query.getPred() instanceof QueryVar
							&& inner_query.getPred().getValue().equals(
									var.getValue())) {
						where_clause.append(String.format(
								"bg%d.pre%s=bg%d.obj%s and", inner_counter,
								inner_query.getPred().toString(), counter, var
										.toString()));
					}

					inner_counter++;
				}
			}
			if (query.getPred() instanceof QueryVar) {

				QueryVar var = (QueryVar) query.getPred();
				int inner_counter = 1;
				for (QueryBGP inner_query : bgps) {


					if (inner_query.getTerm1() instanceof QueryVar
							&& inner_query.getTerm1().getValue().equals(
									var.getValue())) {
						where_clause.append(String.format(
								"bg%d.sub%s=bg%d.pre%s and ", inner_counter,
								inner_query.getTerm1().toString(), counter, var
										.toString()));
					}
					if (inner_query.getTerm2() instanceof QueryVar
							&& inner_query.getTerm2().getValue().equals(
									var.getValue())) {
						where_clause.append(String.format(
								"bg%d.obj%s=bg%d.pre%s and ", inner_counter,
								inner_query.getTerm2().toString(), counter, var
										.toString()));
					}
					if (inner_query.getPred() instanceof QueryVar
							&& inner_query.getPred().getValue().equals(
									var.getValue())) {
						where_clause.append(String.format(
								"bg%d.pre%s=bg%d.pre%s and ", inner_counter,
								inner_query.getPred().toString(), counter, var
										.toString()));
					}
					inner_counter++;
				}
			}
			counter++;
		}
		
		if (where_clause.length() > 0) {
			where_clause.delete(where_clause.length() - 4, where_clause.length());
		}

		return String.format("SELECT %s\nFROM %s\nWHERE %s\n", select_clause,
				from_clause, where_clause);
	}

}
