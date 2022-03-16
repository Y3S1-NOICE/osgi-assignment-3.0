package doctorconsumer.consume;

import java.util.Scanner;

import pharmacyproducer.service.IPharmacyService;

public class PrescriptionConsumer {
	private IPharmacyService pharmacyService;
	private Scanner sc;
	private String doctorNic;
	
	public PrescriptionConsumer(IPharmacyService pharmacyService, String doctorNic) {
		this.pharmacyService = pharmacyService;
		this.sc = new Scanner(System.in);
		this.doctorNic = doctorNic;
	}
	
	/**
	 * Creating prescription records in the database using pharmacyService producer.
	 */
	public void createPrescription() {
		System.out.println("\n>>WRITE PRESCRIPTION...\n");
		System.out.print("Enter patient's NIC: ");
		String patientNic = sc.next();
		
		System.out.print("Enter prescription details: ");
		String details = sc.next();
		
		pharmacyService.createPrescription(doctorNic, patientNic, details);
		
		System.out.println("Record Created. :)");
	}
}
