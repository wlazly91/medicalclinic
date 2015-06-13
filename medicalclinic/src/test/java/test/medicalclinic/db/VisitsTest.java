package test.medicalclinic.db;

import java.sql.SQLException;
import java.sql.Time;
import java.util.GregorianCalendar;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.db.Patient;
import medicalclinic.db.ScheduleVisits;
import medicalclinic.model.VisitsManager;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class VisitsTest {

	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	@Test
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void test() throws HibernateException, SQLException {
		List<ScheduleVisits> result = null;
		String hql = "from ScheduleVisits order by hours_sv asc";
		
		ScheduleVisits schVisit = new ScheduleVisits();
		Clinics clin = new Clinics();
		GregorianCalendar data = new GregorianCalendar(2015,5,12);
		Doctor doc = new Doctor();
		Patient pat = new Patient();
		@SuppressWarnings("deprecation")
		Time a = new Time(18, 30, 00);
		a.getTime();
		a.setHours(19);
		a.setMinutes(0);
		a.setSeconds(0);
		clin.setId(2);
		doc.setId(301);
		pat.setIdPatient(1);
		schVisit.setIdClinics(clin);
		schVisit.setIdDoctor(doc);
		schVisit.setIdPatient(pat);
		schVisit.setDateSV(data.getTime()); 
		schVisit.setHoursSV(a);
		
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(schVisit);
		Query query = session.createQuery(hql);
		session.getTransaction().commit();
		
		result = query.list();
		session.close();
		
		System.out.println(result.get(0).getHoursSV());
	}
	
	@Test
	public void test1() {
		VisitsManager vM = new VisitsManager();
		vM.getFreeTermDoctor(321);
	}
}
