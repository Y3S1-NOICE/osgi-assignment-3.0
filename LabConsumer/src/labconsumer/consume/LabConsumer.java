package labconsumer.consume;

import static labconsumer.util.Util.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import laboratoryproducer.service.ILaboratoryService;

public class LabConsumer {

	private Scanner sc;
	private ILaboratoryService labService;
	
	public LabConsumer(ILaboratoryService labService) {
		this.labService = labService;
		sc = new Scanner(System.in);
	}
	
	public void displayLabRequests() {
		ResultSet rs = labService.seeAllLabRequests();
		System.out.println("\n>>LAB REQUESTS...\n");
		try {
			if (rs.wasNull()) {
				System.out.println("No requests avaialable :(");
			} else {
				while(rs.next()) {
					System.out.println("Patient NIC: "+rs.getString(NIC) + " Test Name: " + rs.getString(TEST_NAME));
				}
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println("Something went wrong while retrieving lab requests "+e.getMessage());
		}
	}
	
	public void createLabResults() {
		System.out.println("\n>CREATE LAB RESULTS...\n");
		System.out.print("Enter patient NIC: ");
		String patientNIC = sc.next();
		
		System.out.print("Enter test name: ");
		String testName = sc.next();
		
		System.out.print("Enter results: ");
		String result = sc.next();
		
		System.out.print("Enter cost: ");
		String price = sc.next();
		
		labService.createLabTestResult(patientNIC, testName, result, price, NOT_PAID);
		
		System.out.println("Record Created :)\n");
	}
}
