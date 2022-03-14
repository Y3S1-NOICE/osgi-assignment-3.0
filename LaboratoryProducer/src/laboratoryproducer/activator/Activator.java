package laboratoryproducer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import databaseproducer.service.IDatabaseService;
import laboratoryproducer.service.ILaboratoryService;
import laboratoryproducer.service.LaboratoryServiceImpl;
import userstoreproducer.service.IUserService;

public class Activator implements BundleActivator {

	private ServiceReference dbReference;
	private ServiceReference userReference;
	private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) {
		System.out.println("Lab is starting...");
		dbReference = context.getServiceReference(IDatabaseService.class.getName());
		IDatabaseService databaseService = (IDatabaseService) context.getService(dbReference);
		
		dbReference = context.getServiceReference(IUserService.class.getName());
		IUserService userService = (IUserService) context.getService(dbReference);
		
		ILaboratoryService laboratoryService = new LaboratoryServiceImpl(databaseService, userService);
		serviceRegistration = context.registerService(ILaboratoryService.class.getName(), laboratoryService, null);
		
	}
		
	public void stop(BundleContext context) throws Exception {
		System.out.println("Lab is stopped...");
		context.ungetService(dbReference);
		serviceRegistration.unregister();
	}

}
