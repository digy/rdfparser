package unibz.it.edu;

import java.io.File;

import unibz.it.edu.parsers.RDFParser;
import unibz.it.edu.parsers.TurtleParser;
import unibz.it.edu.rdfElements.RDFGraph;

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
		RDFParser rdf = new RDFParser();
		RDFGraph data = rdf.decode(file);
		RDFSExpander exp = new RDFSExpander();
		exp.expand(data);
		
		System.out.println(TurtleParser.encode(data).toString());

	}
}
