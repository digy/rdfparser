package unibz.it.edu;

import java.io.File;
import java.io.IOException;

import unibz.it.edu.db.DBManager;
import unibz.it.edu.parsers.RDFXMLParser;
import unibz.it.edu.rdfElements.Graph;

public class Launcher {

	public static void main(String args[]) throws IOException, ClassNotFoundException {

		if (args.length == 0) {
			System.out.println("Please provide an input file");
			return;
		}

		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("Data Input File doesn't exist");
			return;
		}
		RDFXMLParser rdf = new RDFXMLParser();
		Graph data = rdf.decode(file);
		DBManager db = new DBManager();
		db.dumpData(data);

	

	}
}
