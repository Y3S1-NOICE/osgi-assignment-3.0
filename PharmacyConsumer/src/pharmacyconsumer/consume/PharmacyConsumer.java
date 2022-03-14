package pharmacyconsumer.consume;

import static pharmacyconsumer.util.Util.*;

import java.sql.ResultSet;
import java.util.Scanner;

import pharmacyproducer.service.IPharmacyService;

public class PharmacyConsumer {

	private Scanner sc;
	private IPharmacyService pharmacyService;
	
	public PharmacyConsumer(IPharmacyService pharmacyService) {
		this.sc = new Scanner(System.in);
		this.pharmacyService = pharmacyService;
	}
	
	public void seeTodoPrescriptions() {
		ResultSet rs = pharmacyService.seePrescriptions(TODO);
		System.out.println("\n>>PRESCRIPTIONS\n");
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
		System.out.println("\n>>READY MEDICINE\n");
		
		System.out.print("Enter patient's NIC to ready medicine: ");
		String patientNic = sc.next();
		
		System.out.print("Enter prescription Id: ");
		String prescriptionId = sc.next();
		
		System.out.print("Enter the price: ");
		String price = sc.next();
		
		System.out.println("Medicine is ready now :)\n");
		
		pharmacyService.makeMedicineReady(patientNic, prescriptionId, price, NOT_PAID);
	}
	
}
