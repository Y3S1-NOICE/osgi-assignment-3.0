package doctorchannel.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import databaseproducer.service.IDatabaseService;
import userstoreproducer.service.IUserService;

import static doctorchannel.util.DBQueries.*;

public class ChannelServiceImpl implements IChannelService {
	private IUserService userService;
	private PreparedStatement preparedStatement;
	private Connection connection;
	
	public ChannelServiceImpl(IDatabaseService dbService, IUserService userService) {
		this.userService = userService;
		this.preparedStatement = null;
		this.connection = dbService.getDatabaseConnection();
	}
	
	/**
	 * This method will create a doctor channel record in the database. 
	 * Also will create a 'channel' table if does not exist one
	 */
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
	
	/**
	 * This method will retrieve records in 'channel' table 
	 * according to the given patient nic
	 */
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

	/**
	 * This method will delete channel records in the table according 
	 * to given channelId
	 */
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
