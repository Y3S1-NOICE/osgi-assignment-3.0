package doctorconsumer.consume;

import java.util.Scanner;

import doctorproducer.service.IDoctorService;
import patientproducer.service.IPatientService;

public class DoctorConsumer {
	private IDoctorService doctorService;
	private Scanner sc;
	private String doctorNic;
	
	public DoctorConsumer(IDoctorService doctorService, String doctorNic) {
		this.doctorService = doctorService;
		this.sc = new Scanner(System.in);
		this.doctorNic = doctorNic;
	}
	
	/**
	 * Setting doctor status available with the use of docotorService producer.
	 */
	public void setDoctorAvailabe() {
		doctorService.setAvailability(doctorNic, true);
		System.out.println("You are available now :)");
	}
	
	/**
	 * Setting doctor status available with the use of docotorService producer.
	 */
	public void setDoctorUnavailable() {
		doctorService.setAvailability(doctorNic, false);
		System.out.println("You are unavailable now :(");
	}
}
