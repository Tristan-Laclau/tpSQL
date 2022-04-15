package fr.fms.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BddConnection {
	
	private static Properties prop;
	private static String url;
	private static String user;
	private static String password;
	private static Connection connection = null;
	
	
	static {
		try {
		prop = new Properties();
		FileInputStream in = new FileInputStream("config.properties");
		
		prop.load(in);
		in.close();

		// Extraction des propriétés
		url = prop.getProperty("db.url");
		user = prop.getProperty("db.user");
		password= prop.getProperty("db.password");
		
		} catch (Exception e) {
			System.out.println("erreur");
		}
		try {
			Class.forName(prop.getProperty("db.driver.class"));
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}


	public static Connection getConnection() {
		return connection;
		
	}
	
}
