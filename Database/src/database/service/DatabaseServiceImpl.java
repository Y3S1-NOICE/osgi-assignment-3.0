package database.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseServiceImpl implements IDatabaseService {
	private Connection connection;
	private final String diverName;
	private String databaseConnectionLink;
	private String databaseUser;
	private String databasePassword;
	
	public DatabaseServiceImpl() {
		this.diverName = "com.mysql.jdbc.Driver";
		this.databaseConnectionLink = "jdbc:mysql://localhost:3306/test_db?characterEncoding=latin1&useConfigs=maxPerformance";
		this.databaseUser = "root";
		this.databasePassword = "password";
	}

	@Override
	public Connection getDatabaseConnection() {
		try {
			Class.forName(diverName);
			connection = (Connection) DriverManager.getConnection(databaseConnectionLink, databaseUser, databasePassword);
			System.out.println("connection created");
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
