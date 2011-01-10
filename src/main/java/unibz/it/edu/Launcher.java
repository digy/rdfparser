package unibz.it.edu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import unibz.it.edu.db.DBManager;
import unibz.it.edu.entailment.RDFEntailment;
import unibz.it.edu.entailment.RDFSEntailment;
import unibz.it.edu.entailment.SimpleEntailemnt;
import unibz.it.edu.parsers.RDFXMLParser;
import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.sparql.QueryParser;
import unibz.it.edu.sparql.QueryVar;

public class Launcher {

	public static void main(String args[]) throws IOException,
			ClassNotFoundException {

		if (args.length == 0) {
			System.out.println("No arguments given");
			return;
		}
		if ("import".equals(args[0])) {
			String fname = args[2];
			RDFXMLParser parser = new RDFXMLParser();
			Graph parsed = parser.decode(new File(fname));

			System.out.println("Importing data from file: " + args[2]);

			if ("simple".equals(args[1])) {
				SimpleEntailemnt.expand(parsed);
			} else if ("rdf".equals(args[1])) {
				RDFEntailment.exapnd_axioms(parsed);
				RDFEntailment.expand(parsed);
			} else if ("rdfs".equals(args[1])) {
				RDFEntailment.exapnd_axioms(parsed);
				RDFSEntailment.exapnd_axioms(parsed);
				RDFSEntailment.expand(parsed);
			}
			DBManager db = new DBManager();
			db.dumpData(parsed);
			System.out.println("Succesfully imported data to database");
		} else if ("query".equals(args[0])) {
			QueryParser spq = new QueryParser(args[1]);
			DBManager db = new DBManager();
			List<List<String>> table = db.query(spq);
			
			List<String> headers = new ArrayList<String>();
			for (QueryVar var: spq.getHeadVariables()) {
				headers.add(var.getValue());
			}

			Utils.tablePrint(headers, table);
			
		}

	}
}
