package patientproducer.service;

import static patientproducer.util.DBQueries.*;
import static patientproducer.util.Util.*;
import static userstoreproducer.queries.DBqueries.GET_USERS_BY_ROLE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import databaseproducer.service.IDatabaseService;
import userstoreproducer.service.IUserService;

public class PatientServiceImpl implements IPatientService {
	private PreparedStatement preparedStatement;
	private IUserService userService;
	private Connection connection;
	
	public PatientServiceImpl(IDatabaseService databaseService, IUserService userService) {
		this.userService = userService;
		this.connection = databaseService.getDatabaseConnection();
		preparedStatement = null;
	}
	
	@Override
	public boolean patientLogin(String nic, String password) {
		return userService.login(nic, password, ROLE_PATIENT);
	}

	@Override
	public void registerPatient(String nic, String fName, String lName, String password) {
		userService.createUser(nic, fName, lName, ROLE_PATIENT, password);
		
	}

	@Override
	public void setPatientRecords(String nic, String gender, String age, String condition) {
		try {
			preparedStatement = connection.prepareStatement(CREATE_PATIENT_RECORD_QUERY);
			preparedStatement.setString(1, nic);
			preparedStatement.setString(2, gender);
			preparedStatement.setString(3, age);
			preparedStatement.setString(4,  condition);
			preparedStatement.execute(CREATE_PATIENT_RECORDS_TABLE_IF_NOT_AVAILABLE_QUERY);
		} catch (Exception e) {
			System.out.println("Something went wrong when creating patient records table! "+e.getMessage());
		} try {
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Something went wrong when inserting patient record! "+e.getMessage());
		}
		
	}

	@Override
	public ResultSet getPatientRecords(String nic) {
		try {
			preparedStatement = connection.prepareStatement(GET_PATIENT_RECORD);
			preparedStatement.setString(1, nic);
			return preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println("Something went wrong when retrieving patient record! "+e.getMessage());
			return null;
		}
	}
}
