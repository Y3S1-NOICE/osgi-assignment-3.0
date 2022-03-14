package doctorsubscriber.consume;

import java.util.Scanner;
import pharmacy.service.IPharmacyService;

public class PrescriptionConsumer {
	private IPharmacyService pharmacyService;
	private Scanner sc;
	private String doctorNic;
	
	public PrescriptionConsumer(IPharmacyService pharmacyService, String doctorNic) {
		this.pharmacyService = pharmacyService;
		this.sc = new Scanner(System.in);
		this.doctorNic = doctorNic;
	}
	
	public void createPrescription() {
		System.out.println("Enter patient's NIC: ");
		String patientNic = sc.next();
		
		System.out.println("Enter prescription details: ");
		String details = sc.next();
		
		pharmacyService.createPrescription(doctorNic, patientNic, details);
		
		System.out.println("Record Created. :)");
	}
}
