package patientpublisher.util;

public class DBQueries {

	public static final String CREATE_PATIENT_RECORDS_TABLE_IF_NOT_AVAILABLE_QUERY = 
			"CREATE TABLE IF NOT EXISTS patientRecords"
		            + "  (patientRecordsId	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		            + "   nic						VARCHAR(20),"
		    		+ "   gender					VARCHAR(20),"
		    		+ "   age						VARCHAR(20),"
					+ "   condition					VARCHAR(20));";
	
	public static final String CREATE_PATIENT_RECORD_QUERY = "INSERT INTO patientRecords(nic, gender, age, condition) VALUES (?, ?, ?, ?)";
	
	public static final String GET_PATIENT_RECORD = "SELECT * FROM patientRecords WHERE nic = ?";
}
