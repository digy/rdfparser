package unibz.it.edu;

import java.io.File;

public class Launcher {

	public static void main(String args[]) {

		if (args.length <= 1) {
			System.out.println("Please provide an input file");
			return;
		}

		File file = new File(args[1]);
		if (!file.exists()) {
			System.out.println("file doesn't exist");
			return;
		}
		RDFParser rdf = new RDFParser();
		RDFGraph data = rdf.decode(file);
		
		TurtleParser.encode(data);

	}
}
