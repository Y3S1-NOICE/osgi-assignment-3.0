package billproducer.util;

public class DBQueries {

	public static String UPDATE_LAB_PAYMENTS = 
			"UPDATE labTestsResults SET payStatus = ? where nic = ?";
	
	public static String UPDATE_MEDICINE_PAYMENTS = 
			"UPDATE issue_medicine SET payStatus = ? where patientNic = ?";
	
	public static String GET_UNPAID_MEDICINE =
			"SELECT * FROM issue_medicine WHERE payStatus = ? AND patientNic = ?";
	
	public static String GET_UNPAID_LAB_TESTS =
			"SELECT * FROM labTestsResults WHERE payStatus = ? AND nic = ?";
}
