package medicalclinic.model;

import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Users;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


public class VisitsManagement extends WebSecurityConfigurerAdapter{
	
	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	/**
	 * Metoda s³u¿y do rejestrowania siê do lekarza
	 * 
	 * */
	@SuppressWarnings("unused")
	public boolean saveMeVisits() {
		
		try {
			User usr = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserManagement um = new UserManagement();
			List<Users> userList = um.getUsers(usr.getUsername());
			
			
			
 
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
