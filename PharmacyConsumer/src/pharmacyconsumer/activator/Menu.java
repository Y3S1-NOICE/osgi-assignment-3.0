package pharmacyconsumer.activator;

import java.util.Scanner;

import pharmacyconsumer.consume.PharmacyConsumer;
import pharmacyconsumer.util.Helper;
import pharmacyproducer.service.IPharmacyService;

public class Menu {
	private Scanner sc;
	private IPharmacyService pharmacyService;
	private String pharmacistNic;
	
	public Menu(IPharmacyService pharmacyService) {
		this.sc = new Scanner(System.in);
		this.pharmacyService = pharmacyService;
		
		while(true) {
			System.out.print("Enter nic: ");
			pharmacistNic = sc.next();
			
			System.out.print("Enter password: ");
			String password = sc.next();
		
			if (pharmacyService.pharmacistLogin(pharmacistNic, password)) {
				break;
			} else {
				System.out.println("NIC or password is incorrect! \n");
			}
		}
		
		System.out.println("\n*****WELCOME TO HOSPITAL APPLICATION*****");
		System.out.println(">>>>PHARMACY PORTAL\n");
	}
	
	public int showMainMenu() {
		System.out.println("Main Manu");
		System.out.println("\t1. See Prescriptions");
		System.out.println("\t2. Ready Medicine");
		
		return Helper.takeInput(2, sc);
	}
	
	public void startTemplate() {
		int selection = showMainMenu();
		PharmacyConsumer pharmacyConsumer = new PharmacyConsumer(pharmacyService);
		
		switch(selection) {
		case 1:
			pharmacyConsumer.seeTodoPrescriptions();
			startTemplate();
			break;
		case 2: 
			pharmacyConsumer.readyMedicine();
			startTemplate();
			break;
		default:
			startTemplate();
		}
	}
}
