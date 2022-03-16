package adminconsumer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import adminconsumer.menu.Menu;
import userstoreproducer.service.IUserService;


public class Activator implements BundleActivator {

	private ServiceReference userServiceRef;

	/**
	 * start method implementation
	 */
	public void start(BundleContext context)  {
		System.out.println("Admin consumer is starting...");
		
		userServiceRef = context.getServiceReference(IUserService.class.getName());
		IUserService userService = (IUserService) context.getService(userServiceRef);
		
		Menu menu = new Menu(userService);
		menu.startTemplate();
	}
	
	/**
	 * stop method implementation
	 */
	public void stop(BundleContext context) throws Exception {
		context.ungetService(userServiceRef);
		System.out.println("Admin consumer is stopping...");
	}
}
