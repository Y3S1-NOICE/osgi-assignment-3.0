package laboratoryproducer.util;

public class DBQueries {
	
	public static final String CREATE_LABTEST_RESULTS_TABLE_IF_NOT_AVAILABLE_QUERY = 
			"CREATE TABLE IF NOT EXISTS labTestsResults"
		            + "  (labTestId	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		            + "   nic						VARCHAR(20),"
		    		+ "   testName							VARCHAR(20),"
		    		+ "   result							VARCHAR(20),"
		    		+ "   cost								VARCHAR(20),"
					+ "   payStatus							VARCHAR(20));";
	
	public static final String CREATE_LABTEST_REQUESTS_TABLE_IF_NOT_AVAILABLE_QUERY = 
			"CREATE TABLE IF NOT EXISTS labTestsRequests"
		            + "  (labTestId	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		            + "   nic						VARCHAR(20),"
					+ "   testName							VARCHAR(20));";
	
	public static final String GET_LABTESTS_RESULTS_QUERY = "SELECT * FROM labTestsResults WHERE nic = ? AND payStatus = ?";
	
	public static final String GET_ALL_LABTESTS_RESULTS_QUERY = "SELECT * FROM labTestsResults WHERE payStatus = ?";
	
	public static final String CREATE_LABTESTS_RESULTS_QUERY = "INSERT INTO labTestsResults(nic, testName, result, cost, payStatus) VALUES (?, ?, ?, ?, ?)";
	
	public static final String CREATE_LABTESTS_REQUESTS_QUERY = "INSERT INTO labTestsRequests(nic, testName) VALUES (?, ?)";
	
	public static final String GET_ALL_LAB_REQUESTS = "SELECT * FROM labTestsRequests";
}
