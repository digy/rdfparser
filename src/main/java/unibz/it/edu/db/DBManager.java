package unibz.it.edu.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import unibz.it.edu.rdfElements.Graph;
import unibz.it.edu.rdfElements.Triple;
import unibz.it.edu.sparql.QueryParser;
import unibz.it.edu.sparql.QueryVar;

public class DBManager {

	private final String hostname;
	private final String port;
	private final String username;
	private final String password;
	private final String database;

	private Connection connect;

	public DBManager() throws IOException {

		Properties configFile = new Properties();
		configFile.load(new FileInputStream("db.config"));
		hostname = configFile.getProperty("hostname");
		port = configFile.getProperty("port");
		username = configFile.getProperty("username");
		password = configFile.getProperty("password");
		database = configFile.getProperty("database");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		String connection_str = String.format("jdbc:postgresql://%s:%s/%s",
				hostname, port, database);
		try {
			connect = DriverManager.getConnection(connection_str, username,
					password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}

	public void dropDB() {
		final String drop_table = "DROP TABLE data;";
		try {
			Statement st = connect.createStatement();
			st.executeUpdate(drop_table);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

	}

	public void dumpData(Graph data) {
		dropDB();
		prepare_table();
		
		String sql = "INSERT INTO data(subject, predicate, object) VALUES((?),(?),(?))";
		try {
		PreparedStatement pstmt = connect.prepareStatement(sql);
		// Insert 10 rows of data 
		List<Triple> triples = data.getTriples();
		for (Triple trp: triples) {
			
			pstmt.setString(1, trp.getSubject().toString());
			pstmt.setString(2, trp.getPredicate().toString());
			pstmt.setString(3, trp.getObject().toString());
			pstmt.addBatch(); 
		} 
		// Execute the batch 
		int [] updateCounts = pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} 
	}

	public void prepare_table() {
		final String sql_table_create = "CREATE TABLE data ("
				+ "subject VARCHAR(100)," + "predicate VARCHAR(100),"
				+ "object VARCHAR(100)" + ");";
		try {
			Statement st = connect.createStatement();
			st.executeUpdate(sql_table_create);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public List<List<String>> query(QueryParser sqp) {
		String sql = sqp.build_sql();
		List<QueryVar> projection_list = sqp.getHeadVariables();
		List<List<String>> results = new ArrayList<List<String>>();
		
		try {
			Statement stmt = connect.createStatement();
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();

				for (int i=0; i< projection_list.size(); ++i) {
					row.add(rs.getString(i+1));
				}
				results.add(row);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return results;
		}
		return results;
	}

}
