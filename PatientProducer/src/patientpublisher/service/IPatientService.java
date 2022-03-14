package patientpublisher.service;

import java.sql.ResultSet;

public interface IPatientService {
	public boolean patientLogin(String nic, String password);
	public void registerPatient(String nic, String fName, String lName, String password);
	public void setPatientRecords(String nic,String gender,String age,String condition);
	public ResultSet getPatientRecords(String nic);
}
