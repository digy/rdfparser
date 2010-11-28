package unibz.it.edu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {
	
	
	/**
	 * Strip comments from result files
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String path) throws IOException {
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
	
	public static String[][] get_fnames(String dirname) {

		File dir = new File(dirname);

		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.endsWith(".rdf") && name.startsWith("test"));
			}
		});

		String[][] rv = new String[files.length][2];

		for (int i = 0; i < files.length; ++i) {
			File in_file = files[i];

			String result_name = in_file.getAbsolutePath().split("\\.")[0]
					+ ".nt";
			rv[i][0] = in_file.getAbsolutePath();
			rv[i][1] = result_name;
		}
		return rv;
	}

}
