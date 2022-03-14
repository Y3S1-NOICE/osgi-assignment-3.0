package doctorsubscriber.menu;

import java.util.Scanner;

import doctorchannel.service.IChannelService;
import doctorpublisher.service.IDoctorService;
import doctorsubscriber.consume.ChannelDoctorConsumer;
import doctorsubscriber.consume.PatientRecordsConsumer;
import doctorsubscriber.consume.PrescriptionConsumer;
import doctorsubscriber.util.Helper;
import patientpublisher.service.IPatientService;
import pharmacy.service.IPharmacyService;

public class Menu {

	private IPharmacyService pharmacyService;
	private IChannelService channelService;
	private IPatientService patientService;
	private IDoctorService doctorService;
	private Scanner sc;
	private String doctorNic;
	
	public Menu(IPharmacyService pharmacyService, IChannelService channelService, IPatientService patientService,
			IDoctorService doctorService) {
		this.pharmacyService = pharmacyService;
		this.channelService = channelService;
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.sc = new Scanner(System.in);
		
		login();
	}
	
	public void login() {
		while(true) {
			System.out.print("Enter nic: ");
			doctorNic = sc.next();
			
			System.out.print("Enter password: ");
			String password = sc.next();
			
			if (doctorService.doctorLogin(doctorNic, password)) {
				break;
			} else {
				System.out.println("NIC or password is incorrect! \n");
			}
		}
		
		System.out.println("\n*****WELCOME TO HOSPITAL APPLICATION*****");
		System.out.println(">>>>DOCTOR PORTAL\n");
	}

	public int displayMainMenu() {
		System.out.println("\nMain Menu");
		System.out.println("\t1. Patient Channelings");
		System.out.println("\t2. See Patient Records");
		System.out.println("\t3. Create Patient Records");
		System.out.println("\t4. Write Prescription");
		
		return Helper.takeInput(4, sc);
	}
	
	public void startTemplate() {
		int selection = displayMainMenu();
		
		switch(selection) {
		case 1:
			ChannelDoctorConsumer channel = new ChannelDoctorConsumer(channelService, doctorNic);
			channel.getChannelList();
			startTemplate();
			break;
		case 2: 
			PatientRecordsConsumer getRecords = new PatientRecordsConsumer(patientService, doctorNic);
			getRecords.getPatientRecords();
			startTemplate();
			break;
		case 3: 
			PatientRecordsConsumer setRecords = new PatientRecordsConsumer(patientService, doctorNic);
			setRecords.setPatientRecords();
			startTemplate();
			break;
		case 4: 
			PrescriptionConsumer prescription = new PrescriptionConsumer(pharmacyService, doctorNic);
			prescription.createPrescription();
			startTemplate();
			break;
		default: 
			startTemplate();
		}
	}
}
