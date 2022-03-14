package patientsubscriber.consume;

import static patientsubscriber.util.Util.*;

import java.sql.ResultSet;
import java.util.Scanner;
import bill.service.IBillService;
import pharmacy.service.IPharmacyService;

public class Pharmacy {

	private IPharmacyService pharmacyService;
	private IBillService billService;
	private Scanner sc;
	private String patientNic;
	
	public Pharmacy(IPharmacyService pharmacyService, IBillService billService, String patientNic) {
		this.pharmacyService = pharmacyService;
		this.billService = billService;
		this.patientNic = patientNic;
	}
	
	public boolean CollectMedicine() {
		ResultSet rs = billService.seeUnpaidMedicines(patientNic);
		
		try {
			if(rs.wasNull()) {
				System.out.println("You dont have medicines to be collected :(");
				return false;
			} else {
				System.out.println("\nMedicines to be collected...");
				while(rs.next()) {
					System.out.println("Prescription ID: " + rs.getString(PRESCRIPTION_ID));
					System.out.println("\tPrice: " + rs.getString(PRICE));
					System.out.println("");
				}
				return true;
			}
		} catch (Exception e) {
			System.out.println("Something went wrong while retrieving medicine results "+e.getMessage());
			return false;
		}
	}
	
	public void payBills() {
		System.out.print("Do you want to pay medicines now (y/n) ?");
		String input = sc.next();
		
		if (input.equalsIgnoreCase("Y")) {
			billService.payAllMedicines(patientNic);
			System.out.println("All are paid now. Thank you :)");
		} 
		//go back
	}
	
	public void startTemplate() {
		if(CollectMedicine()) {
			payBills();
		}
	}
}
