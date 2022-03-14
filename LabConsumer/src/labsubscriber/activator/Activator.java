package labsubscriber.activator;

import laboratory.service.ILaboratoryService;
import labsubscriber.menu.Menu;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private ServiceReference labServiceRef;

	public void start(BundleContext context) {
		System.out.println("Lab subscriber is starting!!!");
		
		labServiceRef = context.getServiceReference(ILaboratoryService.class.getName());
		ILaboratoryService labService = (ILaboratoryService) context.getService(labServiceRef);
		
		Menu menu = new Menu(labService);
		menu.startTemplate();

	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(labServiceRef);
		System.out.println("Lab subscriber Stopped!!!");
	}

}
