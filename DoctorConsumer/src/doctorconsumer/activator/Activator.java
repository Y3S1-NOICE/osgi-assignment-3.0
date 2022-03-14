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
	
	public void start(BundleContext context) {
		System.out.println("Doctor consumer is starting!!!");
		
		patientServiceRef = context.getServiceReference(IPatientService.class.getName());
		IPatientService patientService = (IPatientService) context.getService(patientServiceRef);
		
		channelServiceRef = context.getServiceReference(IChannelService.class.getName());
		IChannelService channelService = (IChannelService) context.getService(channelServiceRef);
		
		doctorServiceRef = context.getServiceReference(IDoctorService.class.getName());
		IDoctorService doctorService = (IDoctorService) context.getService(doctorServiceRef);
		
		pharmacyServiceRef = context.getServiceReference(IPharmacyService.class.getName());
		IPharmacyService pharmacyService = (IPharmacyService) context.getService(pharmacyServiceRef);
		
		Menu menu = new Menu(pharmacyService, channelService, patientService, doctorService);
		menu.startTemplate();
		
	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(patientServiceRef);
		context.ungetService(channelServiceRef);
		context.ungetService(doctorServiceRef);
		context.ungetService(pharmacyServiceRef);
		
		System.out.println("Doctor consumer is Stopped!!!");
	}
}