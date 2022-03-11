package database.service;

import java.sql.Connection;

public interface IDatabaseService {
	public Connection getDatabaseConnection();
}
