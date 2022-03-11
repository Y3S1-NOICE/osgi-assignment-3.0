package doctorchannel.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static doctorchannel.util.DBQueries.*;
import database.service.IDatabaseService;
import userstore.service.IUserService;

public class ChannelServiceImpl implements IChannelService {
	private IUserService userService;
	private PreparedStatement preparedStatement;
	private Connection connection;
	
	public ChannelServiceImpl(IDatabaseService dbService, IUserService userService) {
		this.userService = userService;
		this.preparedStatement = null;
		this.connection = dbService.getDatabaseConnection();
	}
	
	@Override
	public void channelDoctor(String doctorNic, String patientNic, String date) {
		try {
			preparedStatement = connection.prepareStatement(ADD_CHANNEL_RECORD);
			preparedStatement.setString(1, doctorNic);
			preparedStatement.setString(2, patientNic);
			preparedStatement.setString(3, date);
			preparedStatement.execute(CREATE_CHANNEL_TABLE_IF_NOT_EXSIST);
		} catch (Exception e) {
			System.out.println("Something went wrong when creating doctor channel table! "+e.getMessage());
		} 
		
		try {
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Something went wrong when inserting doctor channel record! "+e.getMessage());
		}
	}

	@Override
	public ResultSet seeDoctorChannels(String doctorNic) {
		try {
			preparedStatement = connection.prepareStatement(GET_ALL_CHANNELS);
			preparedStatement.setString(1, doctorNic);
			return preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println("Something went wrong when getting channel details! "+e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteChannels(String channelId) {
		try {
			preparedStatement = connection.prepareStatement(DELETE_CHANNEL_RECORD);
			preparedStatement.setString(1, channelId);
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Something went wrong when getting channel details! "+e.getMessage());
		}
		
	}

	
}
