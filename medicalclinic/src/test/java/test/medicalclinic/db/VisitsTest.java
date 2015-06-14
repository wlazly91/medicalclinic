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
	
//	@Test
	@SuppressWarnings("deprecation")
	public void test1() {
		VisitsManager vM = new VisitsManager();
		List<Time> freeTime = new ArrayList<Time>();
		freeTime = vM.getFreeTermDoctor(321);
		
		for (Time time : freeTime) {
			System.out.println(time.getHours()+":"+time.getMinutes());
		}
	}
	
	@Test
	public void test2() {
	List<ScheduleVisits> result = new ArrayList<ScheduleVisits>();
	String[] resultDoc;
	HashMap<String, ArrayList<ScheduleVisits>> freeTerm = new HashMap<String, ArrayList<ScheduleVisits>>();
	
	int idDoc = 321;
	
	VisitsManager vM = new VisitsManager();
	
	result = vM.termFromDate(idDoc);
	resultDoc = vM.admissionDaysDoctor(idDoc);
	
	for (ScheduleVisits scheduleVisits : result) {
		System.out.println(scheduleVisits.getHoursSV());
	}
	
	for (int i = 0; i < resultDoc.length; i++) {
		System.out.println(resultDoc[i]);
	}
	
//	ArrayList<java.sql.Date> res = new ArrayList<java.sql.Date>();
	
	HashMap<String , ArrayList<java.sql.Date>> res = dateOfAdmission(resultDoc,result.get(0).getDateSV());
	for (int i = 0; i < res.size(); i++) {
		System.out.println(res.get(i));
	}
	}
	@SuppressWarnings("deprecation")
	public static HashMap<String , ArrayList<java.sql.Date>> dateOfAdmission(String[] docDay, Date dateTo) {
		
		HashMap<String , ArrayList<java.sql.Date>> dataPrzyjec = new HashMap<String , ArrayList<java.sql.Date>>();
		
		ArrayList<java.sql.Date> sunday = new ArrayList<java.sql.Date>(); 
		ArrayList<java.sql.Date> monday = new ArrayList<java.sql.Date>(); 
		ArrayList<java.sql.Date> tuesday = new ArrayList<java.sql.Date>(); 
		ArrayList<java.sql.Date> wednesday = new ArrayList<java.sql.Date>(); 
		ArrayList<java.sql.Date> thursday = new ArrayList<java.sql.Date>(); 
		ArrayList<java.sql.Date> friday = new ArrayList<java.sql.Date>(); 
		ArrayList<java.sql.Date> saturday = new ArrayList<java.sql.Date>();
		
		Calendar dateActual = Calendar.getInstance();
		java.sql.Date date = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate());

		DateTime b = new DateTime(dateTo.getTime());
		DateTime a = new DateTime(date.getTime());
		int iloscDni = Days.daysBetween(a,b).getDays();
		String tmp = "";
		for (int i = 0; i <= iloscDni; i++) {
			date = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate() + i);
			for (int j = 0; j < docDay.length; j++) {
				tmp = DateInfo.getDayOfWeekInDate(date);
				if (tmp.equals(docDay[j])){
					switch(tmp) {
						case "Sunday":
							sunday.add(date);
						case "Monday":
							monday.add(date);
						case "Tuesday":
							tuesday.add(date);
						case "Wednesday":
							wednesday.add(date);
						case "Thursday":
							thursday.add(date);
						case "Friday":
							friday.add(date);
						case "Saturday":
							saturday.add(date);
					}
				}
			}
		}
		
		dataPrzyjec.put(HashMapKey.MONDAY, monday);
		dataPrzyjec.put(HashMapKey.TUESDAY, tuesday);
		dataPrzyjec.put(HashMapKey.WEDNESDAY, wednesday);
		dataPrzyjec.put(HashMapKey.THURSDAY, thursday);
		dataPrzyjec.put(HashMapKey.FRIDAY, friday);
		dataPrzyjec.put(HashMapKey.SATURDAY, saturday);
		dataPrzyjec.put(HashMapKey.SUNDAY, sunday);
		
		return dataPrzyjec; 
	}
	
	

	
}
