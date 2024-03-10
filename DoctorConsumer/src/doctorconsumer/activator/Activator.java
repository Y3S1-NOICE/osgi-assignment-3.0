package doctorconsumer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import doctorchannel.service.IChannelService;
import doctorconsumer.menu.Menu;
import doctorproducer.service.IDoctorService;
import patientproducer.service.IPatientService;
import pharmacyproducer.service.IPharmacyService;

public class Activator implements BundleActivator {

	private ServiceReference patientServiceRef;
	private ServiceReference channelServiceRef;
	private ServiceReference doctorServiceRef;
	private ServiceReference pharmacyServiceRef;
	
	/**
	 * Implements start method in lifecycle
	 */
	public void start(BundleContext context) {
		System.out.println("Doctor consumer is starting!!!");
		
		//getting patient service
		patientServiceRef = context.getServiceReference(IPatientService.class.getName());
		IPatientService patientService = (IPatientService) context.getService(patientServiceRef);
		
		//getting channel service
		channelServiceRef = context.getServiceReference(IChannelService.class.getName());
		IChannelService channelService = (IChannelService) context.getService(channelServiceRef);
		
		//getting doctor service
		doctorServiceRef = context.getServiceReference(IDoctorService.class.getName());
		IDoctorService doctorService = (IDoctorService) context.getService(doctorServiceRef);
		
		//getting pharmacy service
		pharmacyServiceRef = context.getServiceReference(IPharmacyService.class.getName());
		IPharmacyService pharmacyService = (IPharmacyService) context.getService(pharmacyServiceRef);
		
		//Creating a Menu object and starting menu template
		Menu menu = new Menu(pharmacyService, channelService, patientService, doctorService);
		menu.startTemplate();
		
	}

	/**
	 * Implements stop method in lifecycle
	 */
	public void stop(BundleContext context) throws Exception {
		context.ungetService(patientServiceRef);
		context.ungetService(channelServiceRef);
		context.ungetService(doctorServiceRef);
		context.ungetService(pharmacyServiceRef);
		
		System.out.println("Doctor consumer is Stopped!!!");
	}
}