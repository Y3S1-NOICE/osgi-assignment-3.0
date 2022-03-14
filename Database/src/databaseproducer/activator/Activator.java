package databaseproducer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import databaseproducer.service.DatabaseServiceImpl;
import databaseproducer.service.IDatabaseService;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Database Publisher service started...");
		IDatabaseService database = new DatabaseServiceImpl();
		serviceRegistration = context.registerService(IDatabaseService.class.getName(), database, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Database Publisher service stopped !!!");
		serviceRegistration.unregister();
	}
}
