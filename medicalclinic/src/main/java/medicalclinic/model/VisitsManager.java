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
	 * Metoda zwraca wolne terminy podanego lekarza 
	 * @param idDoc - id lekarza z bazy danych 
	 * 
	 * */
	@SuppressWarnings({"deprecation", "unused" })
	public List<Time> getFreeTermDoctor(int idDoc) {
		List<Time> freeTime = new ArrayList<Time>();
		List<ScheduleVisits> result = null;
		List<DoctorOfficeHours> resultDocHours = null;
		VisitsManager vM = new VisitsManager();
	
//		session = sessionFactory.openSession();
//		String hql = "from DoctorOfficeHours Doctor where doc.id = " + idDoc;
//		Query query = session.createQuery(hql);
//		resultDocHours = query.list();
//		String hql1 = "from ScheduleVisits Doctor where idDoctor.id = "+ idDoc +" and DATE_SV > '14/06/13' and  DATE_SV < '30/06/13 ' order by hours_sv asc";
//		Query query1 = session.createQuery(hql1);
//		result = query1.list();
//		session.close();

		
		
		
		Time from, to;
		int minute;
		from = resultDocHours.get(0).getHoursFrom();
		to = resultDocHours.get(0).getHoursTo();
		int hoursTo = to.getHours();
		int minuteto = to.getMinutes();

		
		while(hoursTo >= from.getHours()) {
				if(vM.isExsistTime(result, from.getTime())){
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
	 * Metoda sprawdza czy dana godzina jest wolna u danego lekarza
	 * @param List<ScheduleVisits> - lista z umówionymi wizytami lekarza
	 * @param time - long godzina którą sprawdzamy czy jest już zarezerwowana
	 * */
	private boolean isExsistTime(List<ScheduleVisits> schedule, long time) {
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
	 * Metoda zwraca wizyty wszytskie na 7 dni do przodu
	 * @param idDoc - id lekarza z bazy danych
	 * @return List<ScheduleVisits> - zawierająca wszytskie 
	 * wizyt danego lekarza od aktualnej daty do 7 dni w przód
	 * */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<ScheduleVisits> termFromDate(int idDoc) {
		
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
	 * Metoda na podstawie dni w które przyjmuje lekarz zwraca daty jakie to będą 
	 * @param String[] - tablica z nazwami dni w które przyjmuje dany lekarz
	 * @param Date - data do której chcemy sprawdzić terminy
	 * @return Date[] - tablica z datami dni w które lekarz przyjmuje swoich pacjentów
	 * */
	@SuppressWarnings("deprecation")
	public ArrayList<java.sql.Date> dateOfAdmission(String[] docDay, Date dateTo) {
		
		ArrayList<java.sql.Date> dataPrzyjec = new ArrayList<java.sql.Date>();
		Calendar dateActual = Calendar.getInstance();
		java.sql.Date date = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate());

		DateTime b = new DateTime(dateTo.getTime());
		DateTime a = new DateTime(date.getTime());
		int iloscDni = Days.daysBetween(a,b).getDays();
		
		for (int i = 0; i <= iloscDni; i++) {
			date = new java.sql.Date(dateActual.getTime().getYear(), dateActual.getTime().getMonth(), dateActual.getTime().getDate() + i);
			for (int j = 0; j < docDay.length; j++) {
				if (DateInfo.getDayOfWeekInDate(date).equals(docDay[j])) {
					dataPrzyjec.add(date);
					}
				}
			}
			
		return dataPrzyjec; 
	}
}