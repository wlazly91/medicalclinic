package medicalclinic.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.print.Doc;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.db.DoctorOfficeHours;
import medicalclinic.db.Patient;
import medicalclinic.db.ScheduleVisits;
import medicalclinic.db.Users;
import medicalclinic.db.Visits;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@SuppressWarnings("unchecked")
public class VisitsManager extends WebSecurityConfigurerAdapter{
	
	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	
	public List<ScheduleVisits> getMyVisit() {
		
		User usr = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ArrayList<Users> resultUser = new ArrayList<Users>();
		ArrayList<ScheduleVisits> resultVisit = new ArrayList<ScheduleVisits>();
		session = sessionFactory.openSession();
		String userId = "from Users where login = '"+ usr.getUsername() +"'";
		
		Query query = session.createQuery(userId);
		
		resultUser = (ArrayList<Users>) query.list();
		String visitUser = "from Visits where patient = " +resultUser.get(0).getIdUser() + "";
		Query query1 = session.createQuery(visitUser);
		resultVisit = (ArrayList<ScheduleVisits>) query1.list();
		session.close();
		
		
		return resultVisit;
	}
	
	/**
	 * Metoda służy do zarejestrowania pacjenta na wizytę
	 * */
	public boolean saveMeVisits(AppVisits appVisit) {
		
		try {
			User usr = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserManager um = new UserManager();
			List<Users> userList = um.getUsers(usr.getUsername());
			Patient pat = new Patient();
			Doctor doc = new Doctor();
			Clinics clin = new Clinics();
			ScheduleVisits visit = new ScheduleVisits();
			pat.setIdPatient(userList.get(0).getPat().getIdPatient());
			doc.setId(appVisit.getId());
			clin.setId(1);
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			visit.setDateSV(appVisit.getData());
			visit.setHoursSV(appVisit.getTime());
			visit.setIdDoctor(doc);
			visit.setIdPatient(pat);
			visit.setIdClinics(clin);
			
			session.save(visit);
			session.getTransaction().commit();
			session.close();
			return true;
 
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Wolne terminy 7 dni w przód
	 * @return HashMap<String, HashMap<java.sql.Date, ArrayList<Time>>>:
	 * 			Dni Tygodnia
	 * 			Data	
	 * 			Wolne godziny
	 * */
	public LinkedHashMap<String, HashMap<java.sql.Date, ArrayList<Time>>> getFreeTerm(int idDoc) {
		String[] resultDoc;
		List<ScheduleVisits> resultScheduel;
		List<DoctorOfficeHours> doctorDay;
		LinkedHashMap<String, HashMap<java.sql.Date, ArrayList<Time>>> freeTimeinWeek = new LinkedHashMap<String, HashMap<java.sql.Date, ArrayList<Time>>>();
		LinkedHashMap<java.sql.Date, ArrayList<Time>> freeTimeInDay = new LinkedHashMap<java.sql.Date, ArrayList<Time>>();
		LinkedHashMap<String , ArrayList<java.sql.Date>> freeTerm = new LinkedHashMap<String , ArrayList<java.sql.Date>>();
		VisitsManager vM = new VisitsManager();
		List<Time> freeTime = null;
		
		resultDoc = vM.admissionDaysDoctor(idDoc);
		resultScheduel = vM.termFromNextWeek(idDoc);
		
		java.sql.Date dateMax = vM.maxDay(resultScheduel);
		freeTerm = vM.dateOfAdmission(resultDoc, dateMax);
		
		resultScheduel.clear();
		
		for (String key : freeTerm.keySet()) {																															// tutaj nazwa dnia tygodnia
				for (java.sql.Date date : freeTerm.get(key)) {				
					doctorDay = vM.hoursVisitDay(idDoc, key);																					// tutaj date
					resultScheduel = vM.getHoursVisitDay(date, idDoc);
					freeTime = vM.freeTimeInDay(doctorDay.get(0).getHoursFrom(), doctorDay.get(0).getHoursTo(), resultScheduel);
					freeTimeInDay.put(date, (ArrayList<Time>) freeTime);
				}
				freeTimeinWeek.put(key, new LinkedHashMap<java.sql.Date, ArrayList<Time>>(freeTimeInDay));
				freeTimeInDay.clear();
			}
		return freeTimeinWeek;
	}
	
	/**
	 * Metoda zwraca wolne godziny podanego lekarza  
	 * @param from - godzina od ktorej przyjmuje lekarz
	 * @param to - godzina do której przyjmuje dany lekarz
	 * @param schedule - lista wizyt na dany dzień
	 * @return - Lista Wolnych godzin w danym dniu
	 * */
	@SuppressWarnings("deprecation")
	public List<Time> freeTimeInDay(Time from, Time to, List<ScheduleVisits> schedule) {
		
		int hoursTo = to.getHours();
		
		List<Time> freeTime = new ArrayList<Time>();
		VisitsManager vM = new VisitsManager();
		
		while(hoursTo >= from.getHours()) {
			if(vM.isExsistTime(schedule, from.getTime())){
				freeTime.add(new Time(from.getTime()));
				if(from.getMinutes() == 30){
					from.setHours(from.getHours() + 1);
					from.setMinutes(0);
				} else {
					if(to.equals(from))
						break;
					from.setMinutes(30);
				}
			}else {
				if(from.getMinutes() == 30){
					from.setHours(from.getHours() + 1);
					from.setMinutes(0);
				} else {
					if(to.equals(from))
						break;
					from.setMinutes(30);
				}
			}
	}
		
		return freeTime;
	}
	
	
	/**
	 * Zwraca godziny przyjęć lekarza w dni które przyjmuje
	 * */
	public List<DoctorOfficeHours> hoursVisitDay(int idDoc) {
		List<DoctorOfficeHours> resultDocHours;
		session = sessionFactory.openSession();
		String hql = "from DoctorOfficeHours Doctor where doc.id = " + idDoc;
		Query query = session.createQuery(hql);
		resultDocHours = query.list();
		session.close();
		
		return resultDocHours;
	}

	
	/**
	 * Zwraca godziny przyjęć lekarza w podany dzień tygodnia
	 * */
	public List<DoctorOfficeHours> hoursVisitDay(int idDoc, String nameDay) {
		List<DoctorOfficeHours> resultDocHours;
		session = sessionFactory.openSession();
		String hql = "from DoctorOfficeHours Doctor where doc.id = " + idDoc +" and dayOfWeek = '"+ nameDay +"'";
		Query query = session.createQuery(hql);
		resultDocHours = query.list();
		session.close();
		
		return resultDocHours;
	}
	
	
	/**
	 * Metoda sprawdza czy dana godzina jest wolna u danego lekarza
	 * @param List<ScheduleVisits> - lista z umówionymi wizytami lekarza
	 * @param time - long godzina którą sprawdzamy czy jest już zarezerwowana
	 * */
	public boolean isExsistTime(List<ScheduleVisits> schedule, long time) {
		
		if(schedule.size() == 0)
			return true;
		
		for (ScheduleVisits scheduleVisits : schedule) {
			if(scheduleVisits.getHoursSV().getTime() != time)
				return true;
		}
		return false;
	}
	
	
	/**
	 * Medtoda zwraca dni w krótych przyjmuje dany lekarz
	 * @param idDoc - ID lekarza
	 * @return List<DoctorOfficeHours> zaweirającą dni w których przymuje dany lekarz oraz godziny
	 * */
	public String[] admissionDaysDoctor(int idDoc) {
		
		List<DoctorOfficeHours> result = new ArrayList<DoctorOfficeHours>();
		String hql = "from DoctorOfficeHours where doc.id =" + idDoc;
		
		session = sessionFactory.openSession();
				
		Query query = session.createQuery(hql);
		result = query.list();
		
		session.close();
		String[] dayDoc = new String[result.size()];
		
		for (int i = 0 ; i<result.size(); i++) {
			dayDoc[i] = result.get(i).getDayOfWeek();
		}
		
		return dayDoc;
	}
	
	
	/**
	 * Metoda zwraca wizyty na 7 dni do przodu
	 * @param idDoc - id lekarza z bazy danych
	 * @return List<ScheduleVisits> - zawierająca wszytskie 
	 * wizyt danego lekarza od aktualnej daty do 7 dni w przód
	 * */
	@SuppressWarnings("deprecation")
	public List<ScheduleVisits> termFromNextWeek(int idDoc) {
		
		List<ScheduleVisits> result = new ArrayList<ScheduleVisits>();
		Calendar dateActual = Calendar.getInstance();
		
		java.sql.Date actualDay = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate());
		java.sql.Date dateLastVisit = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate() + 7);
		
		String hql = "from ScheduleVisits Doctor where idDoctor.id = "+ idDoc +" and dateSV >= '" + actualDay + "' and dateSV <= '"+ dateLastVisit +"' order by hoursSV asc";
		
		session = sessionFactory.openSession();		
		Query query = session.createQuery(hql);
		result = query.list();
		session.close();
		
		return result;
	}
	
	
	/**
	 * Metoda zwraca godziny wizyt na dany dzień podany
	 * @param idDoc - id lekarza z bazy danych
	 * @param Date - dzień z któremu chcemy pobrać godziny wizyt
	 * @return - List wizyty
	 * */
	public List<ScheduleVisits> getHoursVisitDay(java.sql.Date data, int idDoc) {
		
		List<ScheduleVisits> result = new ArrayList<ScheduleVisits>();		
		String hql = "from ScheduleVisits Doctor where idDoctor.id = "+ idDoc +" and dateSV = '" + data + "' order by hoursSV asc";
		session = sessionFactory.openSession();		
		Query query = session.createQuery(hql);
		result = query.list();
		session.close();
		
		return result;
	}
	
	
//	/**
//	 * Metoda na podstawie dni w które przyjmuje lekarz zwraca daty jakie to będą 
//	 * @param String[] - tablica z nazwami dni w które przyjmuje dany lekarz
//	 * @param Date - data do której chcemy sprawdzić terminy
//	 * @return Date[] - tablica z datami dni w które lekarz przyjmuje swoich pacjentów
//	 * */
//	@SuppressWarnings("deprecation")
//	public ArrayList<java.sql.Date> dateOfAdmission(String[] docDay, Date dateTo) {
//		
//		ArrayList<java.sql.Date> dataPrzyjec = new ArrayList<java.sql.Date>();
//		Calendar dateActual = Calendar.getInstance();
//		java.sql.Date date = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate());
//
//		DateTime b = new DateTime(dateTo.getTime());
//		DateTime a = new DateTime(date.getTime());
//		int iloscDni = Days.daysBetween(a,b).getDays();
//		
//		for (int i = 0; i <= iloscDni; i++) {
//			date = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate() + i);
//			for (int j = 0; j < docDay.length; j++) {
//				if (DateInfo.getDayOfWeekInDate(date).equals(docDay[j])) {
//					dataPrzyjec.add(date);
//					}
//				}
//			}
//			
//		return dataPrzyjec; 
//	}
	
	@SuppressWarnings("deprecation")
	public LinkedHashMap<String , ArrayList<java.sql.Date>> dateOfAdmission(String[] docDay, Date dateTo) {
		
		LinkedHashMap<String , ArrayList<java.sql.Date>> dataPrzyjec = new LinkedHashMap<String , ArrayList<java.sql.Date>>();
		
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
							break;
						case "Monday":
							monday.add(date);
							break;
						case "Tuesday":
							tuesday.add(date);
							break;
						case "Wednesday":
							wednesday.add(date);
							break;
						case "Thursday":
							thursday.add(date);
							break;
						case "Friday":
							friday.add(date);
							break;
						case "Saturday":
							saturday.add(date);
							break;
					}
				}
			}
		}
		
		if(monday.size() > 0)
			dataPrzyjec.put(HashMapKey.MONDAY, monday);
		
		if(tuesday.size() > 0)
			dataPrzyjec.put(HashMapKey.TUESDAY, tuesday);
		
		if(wednesday.size() > 0)
			dataPrzyjec.put(HashMapKey.WEDNESDAY, wednesday);
		
		if(thursday.size() > 0)
			dataPrzyjec.put(HashMapKey.THURSDAY, thursday);
		
		if(friday.size() > 0)
			dataPrzyjec.put(HashMapKey.FRIDAY, friday);
		
		if(saturday.size() > 0)
			dataPrzyjec.put(HashMapKey.SATURDAY, saturday);
		
		if(sunday.size() > 0)
			dataPrzyjec.put(HashMapKey.SUNDAY, sunday);
		
		return dataPrzyjec; 
	}
	
	/**
	 * Metora zwraca maxymalną Datę z listy
	 * */
	public java.sql.Date maxDay(List<ScheduleVisits> result) {
		java.sql.Date max = result.get(0).getDateSV();
		for (ScheduleVisits scheduleVisits : result) {
			if(scheduleVisits.getDateSV().getTime() > max.getTime())
				max = scheduleVisits.getDateSV();
				
		}
		
		return max;
	}
}