package doctorchannel.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import databaseproducer.service.IDatabaseService;
import doctorchannel.service.ChannelServiceImpl;
import doctorchannel.service.IChannelService;
import userstoreproducer.service.IUserService;

public class Activator implements BundleActivator {

	private ServiceReference dbReference;
	private ServiceReference userReference;
	private ServiceRegistration serviceRegistration;
	
	/**
	 * Implements start method in lifecycle.
	 */
	public void start(BundleContext context) {
		System.out.println("Channel is starting...");
		
		//getting database service
		dbReference = context.getServiceReference(IDatabaseService.class.getName());
		IDatabaseService databaseService = (IDatabaseService) context.getService(dbReference);
		
		//getting user service
		userReference = context.getServiceReference(IUserService.class.getName());
		IUserService userService = (IUserService) context.getService(userReference);
		
		//registering channel service
		IChannelService channelService = new ChannelServiceImpl(databaseService, userService);
		serviceRegistration = context.registerService(IChannelService.class.getName(), channelService, null);
		
	}
		
	/**
	 * Implements stop method in lifecycle.
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Channel is stopped...");
		context.ungetService(dbReference);
		context.ungetService(userReference);
		serviceRegistration.unregister();
	}


}
