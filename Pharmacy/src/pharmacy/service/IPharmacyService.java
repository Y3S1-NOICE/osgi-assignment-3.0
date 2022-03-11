package pharmacy.service;

import java.sql.ResultSet;

public interface IPharmacyService {
	public boolean pharmacistLogin(String nic, String password);
	public void createPrescription(String doctorNic, String patientNic, String details);
	public ResultSet seePrescriptions(String patientNic, String todoStatus);
	public void makeMedicineReady(String patientNic, String prescriptionId, String price, String payStatus);
	public ResultSet getIssuedMedicineDetails(String patientNic, String payStatus);
}
