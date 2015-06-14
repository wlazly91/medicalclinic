package medicalclinic.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.DoctorOfficeHours;
import medicalclinic.db.ScheduleVisits;
import medicalclinic.db.Users;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import test.medicalclinic.db.VisitsTest;


public class VisitsManager extends WebSecurityConfigurerAdapter{
	
	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	
	/**
	 * Metoda służy do zarejestrowania pacjenta na wizytę
	 * */
	@SuppressWarnings("unused")
	public boolean saveMeVisits() {
		
		try {
			User usr = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserManager um = new UserManager();
			List<Users> userList = um.getUsers(usr.getUsername());

			session = sessionFactory.openSession();
			session.beginTransaction();
			
			session.save(new String());
			session.getTransaction().commit();
			session.close();
			
 
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	/**
	 * Wolne terminy 7 dni w przód
	 * @return HashMap<String, HashMap<java.sql.Date, ArrayList<Time>>>:
	 * 			Dni Tygodnia
	 * 			Data	
	 * 			Wolne godziny
	 * */
	public HashMap<String, HashMap<java.sql.Date, ArrayList<Time>>> getFreeTerm(int idDoc) {
		String[] resultDoc;
		List<ScheduleVisits> resultScheduel;
		List<DoctorOfficeHours> doctorDay;
		HashMap<String, HashMap<java.sql.Date, ArrayList<Time>>> freeTimeinWeek = new HashMap<String, HashMap<java.sql.Date, ArrayList<Time>>>();
		HashMap<java.sql.Date, ArrayList<Time>> freeTimeInDay = new HashMap<java.sql.Date, ArrayList<Time>>();
		HashMap<java.sql.Date, ArrayList<Time>> freeTimeInDayTmp = new HashMap<java.sql.Date, ArrayList<Time>>();
		HashMap<String , ArrayList<java.sql.Date>> freeTerm = new HashMap<String , ArrayList<java.sql.Date>>();
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
				freeTimeinWeek.put(key, new HashMap<java.sql.Date, ArrayList<Time>>(freeTimeInDay));
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
	public List<Time> freeTimeInDay(Time from, Time to, List<ScheduleVisits> schedule) {
		
		int hoursTo = to.getHours();
		int minuteto = to.getMinutes();
		
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
	
	
	public List<DoctorOfficeHours> hoursVisitDay(int idDoc) {
		List<DoctorOfficeHours> resultDocHours;
		session = sessionFactory.openSession();
		String hql = "from DoctorOfficeHours Doctor where doc.id = " + idDoc;
		Query query = session.createQuery(hql);
		resultDocHours = query.list();
		session.close();
		
		return resultDocHours;
	}

	
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
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ScheduleVisits> termFromNextWeek(int idDoc) {
		
		List<ScheduleVisits> result = new ArrayList<ScheduleVisits>();
		Calendar dateActual = Calendar.getInstance();
		
		java.sql.Date actualDay = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate());
		java.sql.Date dateLastVisit = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate() + 11);
		
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
	@SuppressWarnings("unchecked")
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
	public HashMap<String , ArrayList<java.sql.Date>> dateOfAdmission(String[] docDay, Date dateTo) {
		
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