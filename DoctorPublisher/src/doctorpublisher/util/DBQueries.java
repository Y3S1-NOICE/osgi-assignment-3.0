package doctorpublisher.util;

public class DBQueries {

	public static final String CREATE_DOCTOR_AVAILABILITY_TABLE_IF_NOT_EXSIST = 
			"CREATE TABLE IF NOT EXISTS doctor_availability"
		            + "  (id	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		            + "   nic								VARCHAR(20),"
		            + "   fName								VARCHAR(20),"
					+ "   lName								VARCHAR(20));";
	
	public static final String ADD_AVAILABLE_DOCTOR = 
			 "INSERT INTO doctor_availability(nic, fName, lName ) VALUES (?, ?, ?);";
	
	public static final String REMOVE_AVAILABLE_DOCTOR =
			 "DELETE FROM doctor_availability WHERE nic= ? ";
	
	public static final String GET_AVAILABLE_DOCTORS =
			 "SELECT * FROM doctor_availability";
}
