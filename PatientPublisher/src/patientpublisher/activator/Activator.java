package patientpublisher.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import database.service.IDatabaseService;
import patientpublisher.service.IPatientService;
import patientpublisher.service.PatientServiceImpl;
import userstore.service.IUserService;
import userstore.service.UserService;

public class Activator implements BundleActivator {

	private ServiceReference dbReference;
	private ServiceReference userReference;
	private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) {
		System.out.println("Patient is starting...");
		dbReference = context.getServiceReference(IDatabaseService.class.getName());
		IDatabaseService databaseService = (IDatabaseService) context.getService(dbReference);
		
		dbReference = context.getServiceReference(IUserService.class.getName());
		IUserService userService = (IUserService) context.getService(dbReference);
		
		IPatientService patientService = new PatientServiceImpl(databaseService, userService);
		serviceRegistration = context.registerService(IPatientService.class.getName(), patientService, null);
		
	}
		
	public void stop(BundleContext context) throws Exception {
		System.out.println("Patient is stopped...");
		context.ungetService(dbReference);
		serviceRegistration.unregister();
	}

}
