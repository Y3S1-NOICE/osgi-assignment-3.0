package pharmacy.util;

public class DBQueries {
	public static final String CREATE_PRESCRIPTION_TABLE_IF_NOT_AVAILABLE_QUERY = 
			"CREATE TABLE IF NOT EXISTS prescription"
		            + "  (prescriptionId	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		            + "   doctorNic						VARCHAR(20),"
		    		+ "   patientNic					VARCHAR(20),"
		    		+ "   details						VARCHAR(20),"
					+ "   status						VARCHAR(20));";
	
	public static final String CREATE_ISSUE_MEDICINE_TABLE_IF_NOT_AVAILABLE_QUERY = 
			"CREATE TABLE IF NOT EXISTS issue_medicine"
		            + "  (issue_id	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		            + "   patientNic						VARCHAR(20),"
		    		+ "   prescriptionId					VARCHAR(20),"
		    		+ "   price								VARCHAR(20),"
					+ "   payStatus							VARCHAR(20));";
	
	public static final String CREATE_PRESCRIPTION_QUERY = "INSERT INTO prescription(doctorNic, patientNic, details, status) VALUES (?, ?, ?, ?)";
	
	public static final String GET_PRESCRIPTIONS = "SELECT * FROM prescription WHERE patientNic=? AND status=?";
	
	public static final String GET_ALL_PRESCRIPTIONS = "SELECT * FROM prescription WHERE status=?";
	
	public static final String CREATE_MEDICINE_ISSUE_QUERY = "INSERT INTO issue_medicine(patientNic, prescriptionId, price, payStatus) VALUES (?, ?, ?, ?)";
	
	public static final String GET_ISSUED_MEDICINE_DETAILS_QUERY =  "SELECT * FROM issue_medicine WHERE patientNic=? AND payStatus=?";
}
