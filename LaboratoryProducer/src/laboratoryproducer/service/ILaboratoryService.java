package laboratoryproducer.service;

import java.sql.ResultSet;

public interface ILaboratoryService {

	public void createLabTestResult(String patientNic, String testName, String result, String cost, String payStatus);
	public void requestLabtest(String patientNic, String testName);
	boolean labTesterLogin(String nic, String password);
	public ResultSet seeLabTests(String patientNic, String payStatus);
	public ResultSet seeAllLabRequests();
	
}
