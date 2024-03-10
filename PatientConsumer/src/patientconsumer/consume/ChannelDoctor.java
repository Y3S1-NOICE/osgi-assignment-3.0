package patientconsumer.consume;

import static patientconsumer.util.Util.*;

import java.sql.ResultSet;
import java.util.Scanner;

import doctorchannel.service.IChannelService;
import doctorproducer.service.IDoctorService;

public class ChannelDoctor {

	private IDoctorService doctorService;
	private IChannelService channelService;
	private Scanner sc;
	private String patientNic;
	
	public ChannelDoctor(IDoctorService doctorService, IChannelService channelService, String patientNic) {
		this.sc = new Scanner(System.in);
		this.doctorService = doctorService;
		this.channelService = channelService;
		this.patientNic = patientNic;
	}
	
	public boolean DisplayAvailableDoctors() {
		System.out.println("\n\tAVAILABLE DOCTORS... \n");
		
		ResultSet rs = doctorService.getAvailableDoctors();
		
		try {
			if (rs.wasNull()) {
				System.out.println("Sorry there are no available doctors right now.. :( ");
				return false;
			} else {
				while(rs.next()) {
					System.out.println("ID: " + rs.getString(NIC) + "\t" + "Name: Dr. " + rs.getString(FIRST_NAME) + " " + rs.getString(LAST_NAME));
				}
				return true;
			}
		} catch (Exception e) {
			System.out.println("Something went wrong while retrieving doctors "+e.getMessage());
			return false;
		}
	}
	
	public void channelDoctor() {
		System.out.print("\nEnter doctor's ID to channel: ");
		String doctorNic = sc.next();
				
		System.out.print("Enter the date you want to channel (DD-MM-YYYY): ");
		String date = sc.next();
		
		channelService.channelDoctor(doctorNic, patientNic, date);
		
		System.out.println("\nRecord created :) ");
		//need to go back.
	}
	
	public void startTemplate() {		
		if (DisplayAvailableDoctors()) {
			channelDoctor();
		} 
	}
}
