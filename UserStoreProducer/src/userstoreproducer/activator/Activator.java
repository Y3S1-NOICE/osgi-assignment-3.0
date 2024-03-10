package userstoreproducer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import databaseproducer.service.IDatabaseService;
import userstoreproducer.service.IUserService;
import userstoreproducer.service.UserService;

public class Activator implements BundleActivator {

	private ServiceReference dbReference;
	private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) {
		System.out.println("UserStore publisher is starting...");
		dbReference = context.getServiceReference(IDatabaseService.class.getName());
		IDatabaseService databaseService = (IDatabaseService) context.getService(dbReference);
		
		IUserService userService = new UserService(databaseService);
		serviceRegistration = context.registerService(IUserService.class.getName(), userService, null);
		
	}
		
	public void stop(BundleContext context) throws Exception {
		System.out.println("UserStore publisher is stopping...");
		context.ungetService(dbReference);
		serviceRegistration.unregister();
	}
}
