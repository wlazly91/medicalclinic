package medicalclinic.model;

import java.sql.Time;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.DoctorOfficeHours;
import medicalclinic.db.ScheduleVisits;
import medicalclinic.db.Users;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


public class VisitsManager extends WebSecurityConfigurerAdapter{
	
	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	/**
	 * Metoda służy do zarejestrowania pacjenta na wizytę	
	 * 
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
	 * */
	@SuppressWarnings({ "unchecked", "deprecation", "unused" })
	public List<Time> getFreeTermDoctor(int idDoc) {
		List<Time> freeTime = null;
		List<ScheduleVisits> result = null;
		List<DoctorOfficeHours> resultDocHours;
		session = sessionFactory.openSession();
		VisitsManager vM = new VisitsManager();
		
		String hql = "from DoctorOfficeHours Doctor where doc.id = " + idDoc;
		Query query = session.createQuery(hql);
		resultDocHours = query.list();
		
		String hql1 = "from ScheduleVisits order by hours_sv asc";
		Query query1 = session.createQuery(hql1);
		result = query1.list();
		
		Time from, to;
		int minute;
		from = resultDocHours.get(0).getHoursFrom();
		to = resultDocHours.get(0).getHoursTo();
		int hoursTo = to.getHours();
		int minuteto = to.getMinutes();
		

		
		while(hoursTo >= from.getHours()) {
				if(vM.isExsistTime(result, from.getTime())){
					freeTime.add(from);
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
	 * */
	private boolean isExsistTime(List<ScheduleVisits> schedule, long time) {
		
		for (ScheduleVisits scheduleVisits : schedule) {
			if(scheduleVisits.getHoursSV().getTime() != time)
				return true;
		}
		
		return false;
	}
}