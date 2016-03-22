package test.medicalclinic.db;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.db.DoctorOfficeHours;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class DoctorOfficeHoursTest {

	@Test
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
		
		clin.setName("Dzieciêca");
		session.save(clin);
		
		doctorHours.setIdClinics(clin);
		doctorHours.setDoctor(doc);
		doctorHours.setHoursFrom("14:00");
		doctorHours.setHoursTo("18:00");
		
		
		session.save(doctorHours);
		session.getTransaction().commit();	
		session.close();
	}

}
