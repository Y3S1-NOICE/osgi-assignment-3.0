package doctorchannel.service;

import java.sql.ResultSet;

public interface IChannelService {
	public void channelDoctor(String doctorNic, String patientNic, String date);
	public ResultSet seeDoctorChannels(String doctorNic);
	public void deleteChannels(String channelId);
}
