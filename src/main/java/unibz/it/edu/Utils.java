package unibz.it.edu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
	
	public static void tablePrint(List<String> header, List<List<String>> table) {
		
		int[] col_width = new int[header.size()+1];
		
		for (List<String> row : table) {
			
			for (int i=0; i < row.size(); ++i) {
				String col = row.get(i);
				if (col.length() > col_width[i]) {
					col_width[i]  = col.length();
				}
			}
		}
		
		for (int i=0; i < header.size(); ++i) {
			String head = header.get(i);
			if (head.length() > col_width[i]) {
				col_width[i] = head.length();
			}
		}
		
		for (int i=0; i < header.size(); ++i) {
			String head = header.get(i);
			System.out.print(String.format("%"+col_width[i]+"s | ", head));
		}
		System.out.println();
		for (int i=0; i < header.size(); ++i) {
			for (int j=0; j < col_width[i]; ++j) {
				System.out.print("-");
			}
		}
		System.out.println();

		for (List<String> row : table) {
			
			for (int i=0; i < row.size(); ++i) {
				String col = row.get(i);
				System.out.print(String.format("%"+col_width[i]+"s | ",col ));
			}
			System.out.println();
		}
	}

}
