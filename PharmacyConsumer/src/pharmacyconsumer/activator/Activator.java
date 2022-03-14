package pharmacyconsumer.activator;

import java.sql.SQLException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import pharmacyproducer.service.IPharmacyService;

public class Activator implements BundleActivator {

	private ServiceReference pharmacyServiceRef;

	
	public void start(BundleContext context) throws SQLException {
		System.out.println("Pharmacy subscriber Service is starting!!!");
		
		pharmacyServiceRef = context.getServiceReference(IPharmacyService.class.getName());
		IPharmacyService pharmacyService = (IPharmacyService) context.getService(pharmacyServiceRef);
		
		Menu menu = new Menu(pharmacyService);
		menu.startTemplate();

	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(pharmacyServiceRef);
		
		System.out.println("Pharmacy subscriber Service Stopped!!!");
	}


}