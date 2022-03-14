package doctorconsumer.consume;

import static doctorconsumer.util.Util.*;

import java.sql.ResultSet;

import doctorchannel.service.IChannelService;


public class ChannelDoctorConsumer {

	private IChannelService channelService;
	private String doctorNic;
	
	public ChannelDoctorConsumer(IChannelService channelService, String doctorNic) {
		this.channelService = channelService;
		this.doctorNic = doctorNic;
	}
	
	public boolean getChannelList() {
		ResultSet rs = channelService.seeDoctorChannels(doctorNic);
		System.out.println("\n>>CHANNELINGS...\n");
		try {
			if(rs == null) {
				System.out.println("No channels for you :)");
				return false;
			} else {
				while(rs.next()) {
					System.out.println("Patient NIC: " + rs.getString(PATIENT_NIC) + "\tDate: " + rs.getString(DATE));
				}
				return true;
			}

		} catch (Exception e) {
			System.out.println("Something went wrong while getting channel details. " + e.getMessage());
			return false;
		}
	}
	
}
