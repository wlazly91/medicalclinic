package test.medicalclinic.db;

import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.db.DoctorOfficeHours;
import medicalclinic.model.ClinicManager;
import medicalclinic.model.DoctorManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class DoctorOfficeHoursTest {

	//@Test
	public void test() {
		
		AppConfig config = new AppConfig();
		Doctor doc = new Doctor();
		Clinics clin = new Clinics();
		DoctorOfficeHours doctorHours = new DoctorOfficeHours();
		SessionFactory sessionFactory = config.sessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
	
		doc.setName("Anna");
		doc.setSurname("Kowalska");
		doc.setSpecjalityName("Pediatra");
		session.save(doc);
		
		clin.setName("Dziecięca");
		session.save(clin);
		
		doctorHours.setClinic(clin);
		doctorHours.setDoc(doc);
		doctorHours.setHoursFrom("14:00");
		doctorHours.setHoursTo("18:00");
		
		
		session.save(doctorHours);
		session.getTransaction().commit();	
		session.close();
	}
	
	//@Test
	public void test1() {
		ClinicManager cm = new ClinicManager();
		
		cm.getDoctorOfficeHours("Dziecięca");
		
		System.out.println(cm.getDoctorOfficeHours("Dziecięca"));
	}

	@Test
	public void test2() {
 		ClinicManager cm = new ClinicManager();
		
		cm.getDoctorInClinics("Dziecięca");
		
		System.out.println(cm.getDoctorInClinics("Dziecięca"));
	}
	
	@Test
	public void test3(){
		DoctorManager dM = new DoctorManager();
		
		List<DoctorOfficeHours> a = dM.getHoursDoctor(1);
		
		System.out.println(a);
	}
}
