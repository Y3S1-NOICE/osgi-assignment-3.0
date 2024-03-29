package doctorconsumer.consume;

import static doctorconsumer.util.Util.*;

import java.sql.ResultSet;
import java.util.Scanner;

import patientproducer.service.IPatientService;

public class PatientRecordsConsumer {

	private IPatientService patientService;
	private Scanner sc;
	private String doctorNic;
	
	public PatientRecordsConsumer(IPatientService patientService, String doctorNic) {
		this.patientService = patientService;
		this.sc = new Scanner(System.in);
		this.doctorNic = doctorNic;
	}
	
	/**
	 * Creating new patient records with the use of patientService producer
	 */
	public void setPatientRecords() {
		System.out.println("\n>>SET PATIENT RECORDS...\n");
		System.out.print("Enter Patient NIC: ");
		String nic = sc.next();
		
		System.out.print("Enter Patient's age: ");
		String age = sc.next();
		
		System.out.print("Enter Patient's gender: ");
		String gender = sc.next();
		
		System.out.print("Enter Patient's condition: ");
		String condition = sc.next();
		
		patientService.setPatientRecords(nic, gender, age, condition);
		System.out.println("Record created. :)");
	}
	
	/**
	 * retrieving patient records in the database with the use of patientService producer
	 */
	public void getPatientRecords() {
		System.out.println("\n\tPATIENT RECORDS...");
		System.out.print("Enter patient's NIC to get records: ");
		String patientNic = sc.next();
		
		ResultSet rs = patientService.getPatientRecords(patientNic);
		
		try {
			if(rs.wasNull()) {
				System.out.println("No Records found :( ");
			} else {
				while(rs.next()) {
					System.out.println("Gender: " + rs.getString(GENDER));
					System.out.println("Age: " + rs.getString(AGE));
					System.out.println("Condition: " + rs.getString(CONDITION));
				}
			}

		} catch (Exception e) {
			System.out.println("Something went wrong while getting patient records. " + e.getMessage());
		}
	}
	
}
