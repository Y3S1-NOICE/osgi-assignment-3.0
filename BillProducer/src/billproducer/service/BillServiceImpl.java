package billproducer.service;

import static billproducer.util.DBQueries.*;
import static billproducer.util.Utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import databaseproducer.service.IDatabaseService;
import laboratoryproducer.service.ILaboratoryService;
import pharmacyproducer.service.IPharmacyService;

public class BillServiceImpl implements IBillService {

	private ILaboratoryService labService;
	private IPharmacyService pharmacyService;
	private PreparedStatement preparedStatement;
	private Connection connection;
	
	public BillServiceImpl(ILaboratoryService labService, IPharmacyService pharmacyService, IDatabaseService dbService) {
		this.labService = labService;
		this.pharmacyService = pharmacyService;
		this.preparedStatement = null;
		this.connection = dbService.getDatabaseConnection();
	}
	
	@Override
	public void payAllMedicines(String nic) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_MEDICINE_PAYMENTS);
			preparedStatement.setString(1, PAID);
			preparedStatement.setString(2, nic);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Something went wrong when paying medicine bills " + e.getMessage());
		}
	}

	@Override
	public void payAllLabTests(String nic) {
		try {
			preparedStatement = connection.prepareStatement(UPDATE_LAB_PAYMENTS);
			preparedStatement.setString(1, PAID);
			preparedStatement.setString(2, nic);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Something went wrong when paying lab bills " + e.getMessage());
		}
	}

	@Override
	public ResultSet seeUnpaidLabTests(String nic) {
		return labService.seeLabTests(nic, NOT_PAID);
	}

	@Override
	public ResultSet seeUnpaidMedicines(String nic) {
		return pharmacyService.getIssuedMedicineDetails(nic, NOT_PAID);
	}

	
}
