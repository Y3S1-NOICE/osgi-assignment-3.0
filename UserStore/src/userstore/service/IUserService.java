package userstore.service;

import java.sql.ResultSet;

public interface IUserService {
	public boolean login(String nic, String password, String role);
	public void createUser(String nic, String fName, String lName, String role, String password);
	public ResultSet getAllUsers();
	public ResultSet getAllUsers(String role);
	public ResultSet getUserByNicAndRole(String nic, String role);
//	public ResultSet getUserByLname(String lName, String role);
}
