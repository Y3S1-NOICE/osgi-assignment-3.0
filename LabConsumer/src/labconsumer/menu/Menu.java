package labconsumer.menu;

import java.util.Scanner;

import labconsumer.consume.LabConsumer;
import labconsumer.util.Helper;
import laboratoryproducer.service.ILaboratoryService;

public class Menu {
	
	private Scanner sc;
	private ILaboratoryService labService;
	private String labWorkerNic;
	
	public Menu(ILaboratoryService labService) {
		this.labService = labService;
		sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("Enter nic: ");
			labWorkerNic = sc.next();
			
			System.out.print("Enter password: ");
			String password = sc.next();
			
			if (labService.labTesterLogin(labWorkerNic, password)) {
				break;
			} else {
				System.out.println("NIC or password is incorrect! \n");
			}
		}
		
		System.out.println("\n*****WELCOME TO HOSPITAL APPLICATION*****");
		System.out.println(">>>>LABORATORY PORTAL\n");
		
	}
	
	public int displayMenu() {
		System.out.println("Main Menu");
		System.out.println("\t1. See Lab Requests");
		System.out.println("\t2. Create Lab Results");
		
		return Helper.takeInput(2, sc);
	}
	
	public void startTemplate() {
		int selection = displayMenu();
		LabConsumer consumer = new LabConsumer(labService);

		switch(selection) {
			case 1:
				consumer.displayLabRequests();
				startTemplate();
				break;
			case 2:
				consumer.createLabResults();
				startTemplate();
				break;
			default:
				startTemplate();
		}
	}
}
