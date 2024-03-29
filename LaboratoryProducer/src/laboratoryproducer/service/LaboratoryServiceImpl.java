package laboratoryproducer.service;

import static laboratoryproducer.util.DBQueries.*;
import static laboratoryproducer.util.Util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import databaseproducer.service.IDatabaseService;
import userstoreproducer.service.IUserService;

public class LaboratoryServiceImpl implements ILaboratoryService {
	
	private IUserService userService;
	private PreparedStatement preparedStatement;
	private Connection connection;
	
	public LaboratoryServiceImpl(IDatabaseService dbService, IUserService userService) {
		this.userService = userService;
		this.preparedStatement = null;
		this.connection = dbService.getDatabaseConnection();
	}
	
	@Override
	public void createLabTestResult(String patientNic, String testName, String result, String cost, String payStatus) {
		try {
			preparedStatement = connection.prepareStatement(CREATE_LABTESTS_RESULTS_QUERY);
			preparedStatement.setString(1, patientNic);
			preparedStatement.setString(2, testName);
			preparedStatement.setString(3, result);
			preparedStatement.setString(4, cost);
			preparedStatement.setString(5, payStatus);
			preparedStatement.execute(CREATE_LABTEST_RESULTS_TABLE_IF_NOT_AVAILABLE_QUERY);
		} catch (Exception e) {
			System.out.println("Something went wrong when creating lab test results table! "+e.getMessage());
		} 
		
		try {
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Something went wrong when inserting lab test! "+e.getMessage());
		}
	}

	@Override
	public void requestLabtest(String patientNic, String testName) {
		try {
			preparedStatement = connection.prepareStatement(CREATE_LABTESTS_REQUESTS_QUERY);
			preparedStatement.setString(1, patientNic);
			preparedStatement.setString(2, testName);
			preparedStatement.execute(CREATE_LABTEST_REQUESTS_TABLE_IF_NOT_AVAILABLE_QUERY);
		} catch (Exception e) {
			System.out.println("Something went wrong when creating lab test results table! "+e.getMessage());
		} 
		
		try {
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Something went wrong when inserting lab test! "+e.getMessage());
		}
	}

	@Override
	public boolean labTesterLogin(String nic, String password) {
		return userService.login(nic, password, ROLE_LABTESTER);
	}

	@Override
	public ResultSet seeLabTests(String patientNic, String payStatus) {
		try {
			preparedStatement = connection.prepareStatement(GET_LABTESTS_RESULTS_QUERY);
			preparedStatement.setString(1, patientNic);
			preparedStatement.setString(2, payStatus);
			return preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println("Something went wrong when getting lab tests results details! "+e.getMessage());
			return null;
		}
	}
	
	@Override
	public ResultSet seeAllLabRequests() {
		try {
			preparedStatement = connection.prepareStatement(GET_ALL_LAB_REQUESTS);
			return preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println("Something went wrong when getting lab request details! "+e.getMessage());
			return null;
		}
	}

}
