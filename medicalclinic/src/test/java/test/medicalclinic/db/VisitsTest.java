package test.medicalclinic.db;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.db.DoctorOfficeHours;
import medicalclinic.db.Patient;
import medicalclinic.db.ScheduleVisits;
import medicalclinic.model.DateInfo;
import medicalclinic.model.HashMapKey;
import medicalclinic.model.VisitsManager;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Test;

public class VisitsTest {

	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
//	@Test
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
//		schVisit.setDateSV(data.getTime()); 
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
//	
//	@Test
//	@SuppressWarnings("deprecation")
//	public void test1() {
//		VisitsManager vM = new VisitsManager();
//		List<Time> freeTime = new ArrayList<Time>();
//		freeTime = vM.getFreeTermDoctor(321);
//		
//		for (Time time : freeTime) {
//			System.out.println(time.getHours()+":"+time.getMinutes());
//		}
//	}
	
//	@Test
	public void test2() {
		List<ScheduleVisits> result = new ArrayList<ScheduleVisits>();
		String[] resultDoc;
		HashMap<String, ArrayList<ScheduleVisits>> freeTerm = new HashMap<String, ArrayList<ScheduleVisits>>();
	
		int idDoc = 321;
	
		VisitsManager vM = new VisitsManager();
	
		result = vM.termFromNextWeek(idDoc);
		resultDoc = vM.admissionDaysDoctor(idDoc);
	
		for (ScheduleVisits scheduleVisits : result) {
			System.out.println(scheduleVisits.getHoursSV());
		}
	
		for (int i = 0; i < resultDoc.length; i++) {
			System.out.println(resultDoc[i]);
		}
	
		Date max = vM.maxDay(result);
	
	//	ArrayList<java.sql.Date> res = new ArrayList<java.sql.Date>();
	
		HashMap<String , ArrayList<java.sql.Date>> res = vM.dateOfAdmission(resultDoc,max);
	
		for (int i = 0; i < res.size(); i++) {
		System.out.println(res.get(i).get(i).getDate());
		}
	}
	
	@Test
	public void test3() {
	
		VisitsManager vM = new VisitsManager();
		HashMap<String, HashMap<java.sql.Date, ArrayList<Time>>> a = new HashMap<String, HashMap<java.sql.Date, ArrayList<Time>>>();
		a= vM.getFreeTerm(321);
		System.out.println(a);
		
		
		for (String key : a.keySet()) {
			for(java.sql.Date date : a.get(key).keySet()){
				for (int i = 0; i < a.get(key).get(date).size(); i++) {
					System.out.println("Dzień Tygodnia: " + key + " Data: " + date + " godzina: " + a.get(key).get(date).get(i));
				}
			}
		}
	}

}

