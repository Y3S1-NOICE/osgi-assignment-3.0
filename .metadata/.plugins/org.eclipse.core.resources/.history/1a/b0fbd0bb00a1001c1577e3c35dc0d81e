package pharmacy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.service.IDatabaseService;
import userstore.service.IUserService;
import static pharmacy.util.Util.*;
import static pharmacy.util.DBQueries.*;

public class PharmacyServiceImpl implements IPharmacyService {

	private IUserService userService;
	private PreparedStatement preparedStatement;
	private Connection connection;
	
	public PharmacyServiceImpl(IDatabaseService dbService, IUserService userService) {
		this.userService = userService;
		this.preparedStatement = null;
		this.connection = dbService.getDatabaseConnection();
	}
	
	@Override
	public boolean pharmacistLogin(String nic, String password) {
		return userService.login(nic, password, ROLE_PHARMACIST);
	}

	@Override
	public void createPrescription(String doctorNic, String patientNic, String details) {
		try {
			preparedStatement = connection.prepareStatement(CREATE_PRESCRIPTION_QUERY);
			preparedStatement.setString(1, doctorNic);
			preparedStatement.setString(2, patientNic);
			preparedStatement.setString(3, details);
			preparedStatement.setString(4, TODO);
			preparedStatement.execute(CREATE_PRESCRIPTION_TABLE_IF_NOT_AVAILABLE_QUERY);
		} catch (Exception e) {
			System.out.println("Something went wrong when creating prescription table! "+e.getMessage());
		} 
		
		try {
			preparedStatement.execute();
			System.out.println("Inserting prescriprion data");
		} catch (Exception e) {
			System.out.println("Something went wrong when creating prescription! "+e.getMessage());
		}
		
	}

	@Override
	public ResultSet seePrescriptions(String patientNic, String todoStatus) {
		try {
			preparedStatement = connection.prepareStatement(GET_PRESCRIPTIONS);
			preparedStatement.setString(1, patientNic);
			preparedStatement.setString(2, todoStatus);
			return preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println("Something went wrong when getting prescriptions! "+e.getMessage());
			return null;
		}
	}

	@Override
	public void makeMedicineReady(String patientNic, String prescriptionId, String price, String payStatus) {
		try {
			preparedStatement = connection.prepareStatement(CREATE_MEDICINE_ISSUE_QUERY);
			preparedStatement.setString(1, patientNic);
			preparedStatement.setString(2, prescriptionId);
			preparedStatement.setString(3, price);
			preparedStatement.setString(4, payStatus);
			preparedStatement.execute(CREATE_ISSUE_MEDICINE_TABLE_IF_NOT_AVAILABLE_QUERY);
		} catch (Exception e) {
			System.out.println("Something went wrong when creating issue_medicine table! "+e.getMessage());
		} 
		
		try {
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("Something went wrong when making medicine! "+e.getMessage());
		}
		
		
	}

	@Override
	public ResultSet getIssuedMedicineDetails(String patientNic, String payStatus) {
		try {
			preparedStatement = connection.prepareStatement(GET_ISSUED_MEDICINE_DETAILS_QUERY);
			preparedStatement.setString(1, patientNic);
			preparedStatement.setString(2, payStatus);
			return preparedStatement.executeQuery();
		} catch (Exception e) {
			System.out.println("Something went wrong when getting issued medicine details! "+e.getMessage());
			return null;
		}
	}

}
