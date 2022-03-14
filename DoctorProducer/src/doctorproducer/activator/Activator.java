package doctorproducer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import databaseproducer.service.IDatabaseService;
import doctorproducer.service.DoctorServiceImpl;
import doctorproducer.service.IDoctorService;
import userstoreproducer.service.IUserService;

public class Activator implements BundleActivator {

	private ServiceReference dbReference;
	private ServiceReference userReference;
	private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) {
		System.out.println("Doctor is starting...");
		dbReference = context.getServiceReference(IDatabaseService.class.getName());
		IDatabaseService databaseService = (IDatabaseService) context.getService(dbReference);
		
		userReference = context.getServiceReference(IUserService.class.getName());
		IUserService userService = (IUserService) context.getService(userReference);
		
		IDoctorService doctorService = new DoctorServiceImpl(databaseService, userService);
		serviceRegistration = context.registerService(IDoctorService.class.getName(), doctorService, null);
		
	}
		
	public void stop(BundleContext context) throws Exception {
		System.out.println("Doctor is stopped...");
		context.ungetService(dbReference);
		context.ungetService(userReference);
		serviceRegistration.unregister();
	}

}
