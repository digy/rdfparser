package unibz.it.edu;

import java.io.File;

import unibz.it.edu.parsers.RDFXMLParser;
import unibz.it.edu.parsers.TurtleParser;
import unibz.it.edu.rdfElements.Graph;

public class Launcher {

	public static void main(String args[]) {

		if (args.length == 0) {
			System.out.println("Please provide an input file");
			return;
		}

		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("File doesn't exist");
			return;
		}
		RDFXMLParser rdf = new RDFXMLParser();
		Graph data = rdf.decode(file);
		// RDFSExpander exp = new RDFSExpander();
		// exp.expand(data);

		System.out.println(TurtleParser.encode(data).toString());

	}
}
