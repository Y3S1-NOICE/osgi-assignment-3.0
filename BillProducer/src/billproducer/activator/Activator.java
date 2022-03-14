package billproducer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import billproducer.service.BillServiceImpl;
import billproducer.service.IBillService;
import databaseproducer.service.IDatabaseService;
import laboratoryproducer.service.ILaboratoryService;
import pharmacyproducer.service.IPharmacyService;

public class Activator implements BundleActivator {

	private ServiceReference dbReference;
	private ServiceReference pharmacyReference;
	private ServiceReference labTestReference;;
	private ServiceRegistration serviceRegistration;

	public void start(BundleContext context) {
		System.out.println("Bill is starting...");
		
		dbReference = context.getServiceReference(IDatabaseService.class.getName());
		IDatabaseService databaseService = (IDatabaseService) context.getService(dbReference);
		
		pharmacyReference = context.getServiceReference(IPharmacyService.class.getName());
		IPharmacyService pharmacyService = (IPharmacyService) context.getService(pharmacyReference);
		
		labTestReference = context.getServiceReference(ILaboratoryService.class.getName());
		ILaboratoryService labService = (ILaboratoryService) context.getService(labTestReference);
		
		IBillService billService = new BillServiceImpl(labService, pharmacyService, databaseService);
		serviceRegistration = context.registerService(IBillService.class.getName(), billService, null);
		
	}
		
	public void stop(BundleContext context) throws Exception {
		System.out.println("Bill is stopped...");
		context.ungetService(dbReference);
		context.ungetService(pharmacyReference);
		context.ungetService(labTestReference);
		serviceRegistration.unregister();
	}
}
