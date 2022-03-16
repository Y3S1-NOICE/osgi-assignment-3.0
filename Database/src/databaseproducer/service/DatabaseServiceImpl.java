package databaseproducer.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static databaseproducer.util.Util.*;

public class DatabaseServiceImpl implements IDatabaseService {
	private Connection connection;
	
	public DatabaseServiceImpl() {
	}

	/**
	 * This method will create a connection with the database
	 */
	@Override
	public Connection getDatabaseConnection() {
		try {
			Class.forName(DRIVER);
			connection = (Connection) DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
		} catch (ClassNotFoundException exc) {
			System.out.println("Class not found");
			System.out.println(exc.getMessage());
		} catch (SQLException exc) {
			System.out.println("SQL Error !!!");
			System.out.println(exc.getMessage());
		} finally {
			return connection;
		}
	}
	
	
}
