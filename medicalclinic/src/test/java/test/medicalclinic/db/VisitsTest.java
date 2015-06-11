package test.medicalclinic.db;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.db.Patient;
import medicalclinic.db.ScheduleVisits;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class VisitsTest {

	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	@Test
	public void test() throws HibernateException, SQLException {
		ScheduleVisits schVisit = new ScheduleVisits();
		Clinics clin = new Clinics();
		GregorianCalendar data = new GregorianCalendar(2015,5,12);
		Doctor doc = new Doctor();
		Patient pat = new Patient();
		clin.setId(2);
		doc.setId(301);
		pat.setIdPatient(1);
		schVisit.setIdClinics(clin);
		schVisit.setIdDoctor(doc);
		schVisit.setIdPatient(pat);
		schVisit.setDateSV(data.getTime()); 
		schVisit.setHoursSV("14:20");
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(schVisit);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println();
	}

}
