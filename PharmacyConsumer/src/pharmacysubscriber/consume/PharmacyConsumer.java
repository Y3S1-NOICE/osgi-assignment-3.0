package pharmacysubscriber.consume;

import java.sql.ResultSet;
import java.util.Scanner;
import static pharmacysubscriber.util.Util.*;
import pharmacy.service.IPharmacyService;

public class PharmacyConsumer {

	private Scanner sc;
	private IPharmacyService pharmacyService;
	
	public PharmacyConsumer(IPharmacyService pharmacyService) {
		this.sc = new Scanner(System.in);
		this.pharmacyService = pharmacyService;
	}
	
	public void seeTodoPrescriptions() {
		ResultSet rs = pharmacyService.seePrescriptions(TODO);
		try {
			if(rs.wasNull()) {
				System.out.println("No prescriptions available :(");
			} else {
				while(rs.next()) {
					System.out.println("Prescription ID: " +rs.getString(PRESCRIPTION_ID));
					System.out.println("\tPatient NIC: " + rs.getString(PATIENT_NIC));
					System.out.println("\tDoctor NIC: " + rs.getString(DOCTOR_NIC));
					System.out.println("\tDetails: " + rs.getString(DETAILS));
					System.out.println("");
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong while retrieving prescriptions");
		}
	}
	
	public void readyMedicine() {
		System.out.print("Enter patient's NIC to ready medicine: ");
		String patientNic = sc.next();
		
		System.out.print("Enter prescription Id: ");
		String prescriptionId = sc.next();
		
		System.out.println("Enter the price: ");
		String price = sc.next();
		
		pharmacyService.makeMedicineReady(patientNic, prescriptionId, price, NOT_PAID);
	}
	
}
