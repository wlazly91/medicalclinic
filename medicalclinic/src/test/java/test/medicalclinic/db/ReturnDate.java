package test.medicalclinic.db;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.db.Patient;
import medicalclinic.db.ScheduleVisits;
import medicalclinic.model.DateInfo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class ReturnDate {

	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	@Test
	public void test() {
		session = sessionFactory.openSession();
		
		String hql = "from ScheduleVisits";

		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ScheduleVisits> result = query.list();		
		for (ScheduleVisits scheduleVisits : result) {
			System.out.println(scheduleVisits.getDateSV() + DateInfo.getDayOfWeekInDate(scheduleVisits.getDateSV()));
		}
	}
	
	@Test
	public void test1() {
		session = sessionFactory.openSession();
		Clinics clin = new Clinics();
		Doctor doc = new Doctor();
		Patient pat = new Patient();
		ScheduleVisits sch = new ScheduleVisits();
		
		
		clin.setId(1);
		doc.setId(1);
		pat.setIdPatient(1);
		sch.setDateSV(new Date());
		sch.setHoursSV(new Time(15, 0, 0));
		sch.setIdClinics(clin);
		sch.setIdDoctor(doc);
		sch.setIdPatient(pat);
		session.beginTransaction();
			session.save(sch);
		session.getTransaction().commit();
		
		
		String hql = "from ScheduleVisits";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ScheduleVisits> result = query.list();		
		for (ScheduleVisits scheduleVisits : result) {
			System.out.println(scheduleVisits.getDateSV() + DateInfo.getDayOfWeekInDate(scheduleVisits.getDateSV()));
		}
		session.close();
	}

}
