package userstoreproducer.queries;

public class DBqueries {
	public static final String CREATE_USER_TABLE_IF_NOT_AVAILABLE_QUERY = 
			"CREATE TABLE IF NOT EXISTS user"
		            + "  (userId	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		            + "   nic						VARCHAR(20),"
		    		+ "   fName						VARCHAR(20),"
		    		+ "   lName						VARCHAR(20),"
					+ "   role						VARCHAR(20),"
					+ "   password					VARCHAR(20));";
	
	public static final String LOGIN_QUERY = "SELECT * FROM user WHERE role=? AND nic=? AND password =?";
	
	public static final String CREATE_USER_QUERY = "INSERT INTO user(nic, fName, lName, role, password) VALUES (?, ?, ?, ?, ?) ";
	
	public static final String GET_USERS_BY_ROLE = "SELECT * FROM user WHERE role=? ";
	
	public static final String GET_USERS_BY_NIC_AND_ROLE = "SELECT * FROM user WHERE nic=? AND role=? ";
	
	public static final String GET_USERS = "SELECT * FROM user";
	
}
