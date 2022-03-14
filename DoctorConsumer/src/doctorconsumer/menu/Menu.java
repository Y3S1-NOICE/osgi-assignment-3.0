package doctorconsumer.menu;

import java.util.Scanner;

import doctorchannel.service.IChannelService;
import doctorconsumer.consume.ChannelDoctorConsumer;
import doctorconsumer.consume.DoctorConsumer;
import doctorconsumer.consume.PatientRecordsConsumer;
import doctorconsumer.consume.PrescriptionConsumer;
import doctorconsumer.util.Helper;
import doctorproducer.service.IDoctorService;
import patientproducer.service.IPatientService;
import pharmacyproducer.service.IPharmacyService;

public class Menu {

	private IPharmacyService pharmacyService;
	private IChannelService channelService;
	private IPatientService patientService;
	private IDoctorService doctorService;
	private Scanner sc;
	private String doctorNic;
	private ChannelDoctorConsumer channel;
	private PatientRecordsConsumer records;
	private PrescriptionConsumer prescription;
	private DoctorConsumer docConsumer;
	
	public Menu(IPharmacyService pharmacyService, IChannelService channelService, IPatientService patientService,
			IDoctorService doctorService) {
		this.pharmacyService = pharmacyService;
		this.channelService = channelService;
		this.patientService = patientService;
		this.doctorService = doctorService;
		this.sc = new Scanner(System.in);
		
		login();
		
		channel = new ChannelDoctorConsumer(channelService, doctorNic);
		records = new PatientRecordsConsumer(patientService, doctorNic);
		prescription = new PrescriptionConsumer(pharmacyService, doctorNic);
		docConsumer = new DoctorConsumer(doctorService, doctorNic);
	}
	
	public void login() {
		while(true) {
			System.out.print("\nEnter nic: ");
			doctorNic = sc.next();
			
			System.out.print("Enter password: ");
			String password = sc.next();
			
			if (doctorService.doctorLogin(doctorNic, password)) {
				break;
			} else {
				System.out.println("NIC or password is incorrect! ");
			}
		}
		
		System.out.println("\n*****WELCOME TO HOSPITAL APPLICATION*****");
		System.out.println(">>>>DOCTOR PORTAL");
	}

	public int displayMainMenu() {
		System.out.println("\nMain Menu");
		System.out.println("\t1. Patient Channelings");
		System.out.println("\t2. See Patient Records");
		System.out.println("\t3. Create Patient Records");
		System.out.println("\t4. Write Prescription");
		System.out.println("\t5. I'm available");
		System.out.println("\t6. I'm unavailable");
		
		return Helper.takeInput(6, sc);
	}
	
	public void startTemplate() {
		int selection = displayMainMenu();
		
		switch(selection) {
		case 1:
			channel.getChannelList();
			startTemplate();
			break;
		case 2: 
			records.getPatientRecords();
			startTemplate();
			break;
		case 3: 
			records.setPatientRecords();
			startTemplate();
			break;
		case 4: 
			prescription.createPrescription();
			startTemplate();
			break;
		case 5: 
			docConsumer.setDoctorAvailabe();
			startTemplate();
			break;
		case 6: 
			docConsumer.setDoctorUnavailable();
			startTemplate();
			break;
		default: 
			startTemplate();
		}
	}
}
