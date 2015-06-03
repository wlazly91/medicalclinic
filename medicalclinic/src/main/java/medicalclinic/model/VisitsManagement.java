package medicalclinic.model;

import medicalclinic.config.AppConfig;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class VisitsManagement extends WebSecurityConfigurerAdapter{
	
	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	/**
	 * Metoda s³u¿y do rejestrowania siê do lekarza
	 * 
	 * */
	public boolean saveMeVisits() {
		
		try {
//			userDetailsServiceBean().loadUserByUsername(null);
//			UserDetails a = userDetailsService().loadUserByUsername(null);
//			a.getUsername();
			User usr = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			usr.getUsername();
 
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
