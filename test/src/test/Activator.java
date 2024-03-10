package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import databaseproducer.service.IDatabaseService;
import doctorproducer.service.IDoctorService;
import pharmacyproducer.service.IPharmacyService;
import userstoreproducer.service.IUserService;

public class Activator implements BundleActivator {
	
	private ServiceReference dbReference;
	private ServiceReference userStore;
	private ServiceReference doctoRef;
	private ServiceReference pharmacyReference;
	
	public void start(BundleContext context) throws SQLException {
		dbReference = context.getServiceReference(IDatabaseService.class.getName());
		IDatabaseService service = (IDatabaseService) context.getService(dbReference);
		
		userStore = context.getServiceReference(IUserService.class.getName());
		IUserService userService = (IUserService) context.getService(userStore);
		
		pharmacyReference = context.getServiceReference(IPharmacyService.class.getName());
		IPharmacyService pharmacyService = (IPharmacyService) context.getService(pharmacyReference);
		
		doctoRef = context.getServiceReference(IDoctorService.class.getName());
		IDoctorService dService = (IDoctorService) context.getService(doctoRef);
		
//		System.out.println(dService.doctorLogin("200019202675", "123"));
		//dService.setAvailability("200019202675", true);
		
//		ResultSet rs = dService.getAvailableDoctors();
//		while(rs.next()) {
//			System.out.println(rs.getString("lName"));
//		}

		//pharmacyService.createPrescription("d001", "p001", "give medicine");
		
//		userService.createUser("PH002", "Kamal", "Perera", "pharmacist", "123");
		//System.out.println(	userService.login("200019202676", "456", "patient"));
		
		

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Patient Management Service Stopped!!!");
		context.ungetService(dbReference);
		
	}

}
