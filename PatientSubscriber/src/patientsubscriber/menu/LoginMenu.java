package patientsubscriber.menu;

import java.util.Scanner;

import patientpublisher.service.IPatientService;

public class LoginMenu {

	private Scanner sc;
	private String userId;
	private String password;
	private IPatientService patientService;
	
	public LoginMenu(Scanner sc, IPatientService patientService) {
		this.sc = sc;
	}
	
	public boolean login() {
		System.out.println("\n*****WELCOME TO HOSPITAL APPLICATION*****");
		System.out.println("");
		System.out.println("Enter your credentials to Login");
		System.out.print("UserID/NIC: ");
		userId = sc.next();
		System.out.print("Password: ");
		password = sc.next();
		return patientService.patientLogin(userId, password);
	}
}
