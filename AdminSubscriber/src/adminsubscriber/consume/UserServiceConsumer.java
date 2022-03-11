package adminsubscriber.consume;

import java.sql.ResultSet;
import java.util.Scanner;
import static adminsubscriber.util.Utils.*;
import adminsubscriber.util.Helper;
import userstore.service.IUserService;

public class UserServiceConsumer {

	private IUserService userService;
	private Scanner sc;
	
	public UserServiceConsumer(IUserService userService) {
		this.userService = userService;
		sc = new Scanner(System.in);
	}
	
	public String displayUserRoles() {
		System.out.println("\nSelect a user role from below.");
		System.out.println("\t1. doctor");
		System.out.println("\t2. patient");
		System.out.println("\t3. chemist");
		System.out.println("\t4. pharmacist");
		System.out.println("\t5. admin");
		
		int selection = Helper.takeInput(5, sc);
		
		switch(selection) {
			case 1:
				return DOCTOR;
			case 2:
				return PATIENT;
			case 3: 
				return CHEMIST;
			case 4: 
				return PHARMACIST;
			default:
				return ADMIN;
		}
	}
	
	public void addUser() {
		String role = displayUserRoles();
		
		System.out.print("Enter first name: ");
		String fName = sc.next();
		
		System.out.print("Enter last name: ");
		String lName = sc.next();
		
		System.out.print("Enter NIC of new user: ");
		String nic = sc.next();
		
		System.out.print("Enter a password for new user: ");
		String password = sc.next();
		
		userService.createUser(nic, fName, lName, role, password);
		
		System.out.println("User created :)\n");
	}
	
	public boolean getUsers() {
		String role = displayUserRoles();
		
		ResultSet rs = userService.getAllUsers(role);
		
		try {
			if (rs.wasNull()) {
				System.out.println("Sorry there are no users with " + role +" available.. :( ");
				return false;
			} else {
				while(rs.next()) {
					System.out.println("ID: " + rs.getString(NIC) + "\t" + "Name: " + rs.getString(FIRST_NAME) + " " + rs.getString(LAST_NAME));
				}
				return true;
			}
		} catch (Exception e) {
			System.out.println("Something went wrong while retrieving users. "+e.getMessage());
			return false;
		}
	}
}
