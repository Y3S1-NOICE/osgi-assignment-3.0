package patientconsumer.consume;

import static patientconsumer.util.Util.*;

import java.sql.ResultSet;
import java.util.Scanner;

import billproducer.service.IBillService;
import laboratoryproducer.service.ILaboratoryService;
import patientconsumer.util.Helper;

public class LabTest {

	private ILaboratoryService labService;
	private IBillService billService;
	private Scanner sc;
	private String patientNic;
	
	public LabTest(ILaboratoryService labService, IBillService billService, String patientNic) {
		sc = new Scanner(System.in);
		this.labService = labService;
		this.billService = billService;
		this.patientNic = patientNic;
	}
	
	public void requestLabTest() {
		System.out.println("\n\tREQUEST LAB TESTS...\n");
		
		System.out.print("Enter test name: ");
		String testName = sc.next();

		labService.requestLabtest(patientNic, testName);
		System.out.println("\nRecord created :) ");
	}
	
	public boolean displayUnpaidBills() {
		
		ResultSet rs = billService.seeUnpaidLabTests(patientNic);
		
		try {
			if(rs.wasNull()) {
				System.out.println("No test results yet :(");
				return false;
			} else {
				System.out.println("\nLab Tests Results...");
				while(rs.next()) {
					System.out.println("Test Name: " + rs.getString(TEST_NAME));
					System.out.println("\tResult: " + rs.getString(RESULT));
					System.out.println("\tPrice: " + rs.getString(COST));
					System.out.println("");
				}
				return true;
			}
		} catch (Exception e) {
			System.out.println("Something went wrong while retrieving lab results "+e.getMessage());
			return false;
		}
	}
	
	public void payBills() {
		System.out.print("Do you want to pay for lab tests now (y/n) ?");
		String input = sc.next();
		
		if (input.equalsIgnoreCase("Y")) {
			billService.payAllLabTests(patientNic);
			System.out.println("All are paid now. Thank you :)");
		} 
	}
	
	public int labMenu() {
		System.out.println("\n1. Request lab report.");
		System.out.println("2. My lab reports.");
		System.out.println("3. Back.");
		return Helper.takeInput(3, sc);
	}
	
	public void startTemplate() {
		int labMenuSelection = labMenu();
		switch (labMenuSelection) {
		case 1: 
			requestLabTest();
			startTemplate();
			break;
		case 2:
			if(displayUnpaidBills()) {
				payBills();
			}
			startTemplate();
			break;
		case 3: 
			break;
		default: 
			startTemplate();
		}
	}
}
