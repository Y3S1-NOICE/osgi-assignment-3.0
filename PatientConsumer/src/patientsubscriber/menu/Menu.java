package patientsubscriber.menu;

import java.util.Scanner;

import bill.service.IBillService;
import doctorchannel.service.IChannelService;
import doctorpublisher.service.IDoctorService;
import laboratory.service.ILaboratoryService;
import patientpublisher.service.IPatientService;
import patientsubscriber.consume.ChannelDoctor;
import patientsubscriber.consume.LabTest;
import patientsubscriber.consume.Pharmacy;
import patientsubscriber.util.Helper;
import pharmacy.service.IPharmacyService;

public class Menu {

	private Scanner sc;
	private String patientNic;
	private IDoctorService doctorService;
	private IChannelService channelService;
	private ILaboratoryService labService;
	private IBillService billService;
	private IPharmacyService pharmacyService;
	private IPatientService patientService;
	
	public Menu(IDoctorService doctorService, IChannelService channelService, ILaboratoryService labService, 
			IBillService billService, IPharmacyService pharmacyService, IPatientService patientService) {
		this.patientNic = "200019202676";
		this.sc = new Scanner(System.in);
		this.doctorService = doctorService;
		this.channelService = channelService;
		this.labService = labService;
		this.billService = billService;
		this.pharmacyService = pharmacyService;
		this.patientService = patientService;
		
		while(true) {
	
			System.out.print("Enter nic: ");
			patientNic = sc.next();
			
			System.out.print("Enter password: ");
			String password = sc.next();
			
			if (patientService.patientLogin(patientNic, password)) {
				break;
			} else {
				System.out.println("NIC or password is incorrect! \n");
			}
		}
		
		System.out.println("\n*****WELCOME TO HOSPITAL APPLICATION*****");
		System.out.println(">>>>PATIENT PORTAL\n");
	}

	public int displayMainMenu() {

		System.out.println("\nMain Menu");
		System.out.println("\t1. Channel Doctor");
		System.out.println("\t2. Lab Tests ");
		System.out.println("\t3. Pharmacy ");
		
		return Helper.takeInput(3, sc);
	}
	
	public void startTemplate() {
		int menuSelection = displayMainMenu();
		
		switch (menuSelection) {
			case 1: 
				ChannelDoctor channel = new ChannelDoctor(doctorService, channelService, patientNic);
				channel.startTemplate();
				startTemplate();
				break;
			
			case 2: 
				LabTest labTest = new LabTest(labService, billService, patientNic);
				labTest.startTemplate();
				startTemplate();
				break;
				
			case 3: 
				Pharmacy pharmacy = new Pharmacy(pharmacyService, billService, patientNic);
				pharmacy.startTemplate();
				startTemplate();
				break;
			default: 
				startTemplate();
		}
	}
}
