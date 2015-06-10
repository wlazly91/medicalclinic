package test.medicalclinic.db;

import java.sql.Date;
import java.sql.SQLException;

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
	
	@SuppressWarnings("deprecation")
	@Test
	public void test() throws HibernateException, SQLException {
		ScheduleVisits schVisit = new ScheduleVisits();
		Clinics clin = new Clinics();
		Doctor doc = new Doctor();
		Patient pat = new Patient();
		clin.setId(2);
		doc.setId(1);
		pat.setIdPatient(1);
		schVisit.setIdClinics(clin);
		schVisit.setIdDoctor(doc);
		schVisit.setIdPatient(pat);
		schVisit.setDateSV(new Date(2015, 6, 12));
		schVisit.setHoursSV("14:15");
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(schVisit);
		
		session.getTransaction().commit();
		session.close();
	}

}
