package patientsubscriber.activator;

import java.sql.SQLException;
import laboratory.service.ILaboratoryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import patientpublisher.service.IPatientService;
import patientsubscriber.menu.Menu;
import bill.service.IBillService;
import doctorchannel.service.IChannelService;
import doctorpublisher.service.IDoctorService;
import pharmacy.service.IPharmacyService;

public class Activator implements BundleActivator {

	private ServiceReference patientServiceRef;
	private ServiceReference channelServiceRef;
	private ServiceReference doctorServiceRef;
	private ServiceReference labServiceRef;
	private ServiceReference pharmacyServiceRef;
	private ServiceReference billServiceServiceRef;
	
	public void start(BundleContext context) throws SQLException {
		System.out.println("Patient subscriber Service is starting!!!");
		
		patientServiceRef = context.getServiceReference(IPatientService.class.getName());
		IPatientService patientService = (IPatientService) context.getService(patientServiceRef);
		
		channelServiceRef = context.getServiceReference(IChannelService.class.getName());
		IChannelService channelService = (IChannelService) context.getService(channelServiceRef);
		
		doctorServiceRef = context.getServiceReference(IDoctorService.class.getName());
		IDoctorService doctorService = (IDoctorService) context.getService(doctorServiceRef);
		
		labServiceRef = context.getServiceReference(ILaboratoryService.class.getName());
		ILaboratoryService labService = (ILaboratoryService) context.getService(labServiceRef);
		
		pharmacyServiceRef = context.getServiceReference(IPharmacyService.class.getName());
		IPharmacyService pharmacyService = (IPharmacyService) context.getService(pharmacyServiceRef);
		
		billServiceServiceRef = context.getServiceReference(IBillService.class.getName());
		IBillService billService = (IBillService) context.getService(billServiceServiceRef);
		
		Menu menu = new Menu(doctorService, channelService, labService, billService, pharmacyService,patientService);
		menu.startTemplate();

	}

	public void stop(BundleContext context) throws Exception {
		context.ungetService(patientServiceRef);
		context.ungetService(channelServiceRef);
		context.ungetService(doctorServiceRef);
		context.ungetService(labServiceRef);
		context.ungetService(pharmacyServiceRef);
		context.ungetService(billServiceServiceRef);
		
		System.out.println("Patient subscriber Service Stopped!!!");
	}


}
