package medicalclinic.model;

import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Users;

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
	 * Metoda służy ro zarejestrowania pacjnta na wizytę	
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
}
