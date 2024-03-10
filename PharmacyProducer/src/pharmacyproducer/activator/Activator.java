package pharmacyproducer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import databaseproducer.service.IDatabaseService;
import pharmacyproducer.service.IPharmacyService;
import pharmacyproducer.service.PharmacyServiceImpl;
import userstoreproducer.service.IUserService;
import userstoreproducer.service.UserService;

public class Activator implements BundleActivator {

	private ServiceReference dbServiceReference;
	private ServiceReference userServiceReference;
	private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) {
		System.out.println("Pharmacy is starting...");
		
		dbServiceReference = context.getServiceReference(IDatabaseService.class.getName());
		IDatabaseService databaseService = (IDatabaseService) context.getService(dbServiceReference);
		
		userServiceReference = context.getServiceReference(IUserService.class.getName());
		IUserService userService = (IUserService) context.getService(userServiceReference);
		
		IPharmacyService pharamacyService = new PharmacyServiceImpl(databaseService, userService);
		serviceRegistration = context.registerService(IPharmacyService.class.getName(), pharamacyService, null);
		
	}
		
	public void stop(BundleContext context) throws Exception {
		System.out.println("Pharmacy is stopped...");
		context.ungetService(dbServiceReference);
		context.ungetService(userServiceReference);
		serviceRegistration.unregister();
	}
}
