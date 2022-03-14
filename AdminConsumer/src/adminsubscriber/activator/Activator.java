package adminsubscriber.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import userstore.service.IUserService;


public class Activator implements BundleActivator {

	private ServiceReference userServiceRef;

	public void start(BundleContext context)  {
		System.out.println("Admin subscriber Service is starting!!!");
		
		userServiceRef = context.getServiceReference(IUserService.class.getName());
		IUserService userService = (IUserService) context.getService(userServiceRef);
	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(userServiceRef);
		System.out.println("Admin subscriber Service Stopped!!!");
	}
}
