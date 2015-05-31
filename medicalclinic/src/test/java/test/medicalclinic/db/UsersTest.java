package test.medicalclinic.db;

import static org.junit.Assert.*;

import java.sql.SQLException;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Users;
import medicalclinic.model.AppUser;
import medicalclinic.model.UserManagement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class UsersTest {
	
	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	@Test
	public void testUsers1() throws HibernateException, SQLException {

		UserManagement um = new UserManagement();
		AppUser appUser = new AppUser();
		
		appUser.setName("as");
		appUser.setSurname("as");
		appUser.setSpecjality("as");
		appUser.setLogin("as");
		appUser.setPassword("as");
		appUser.setActive(1);
		appUser.setWho("Doctor");
		
		assertTrue(um.addDoctor(appUser));
	}
	
	@Test
	public void testUsers2() {

		Users usr = new Users();
		
		usr.setLogin("Kubuœasd");
		usr.setPassword("OKAJSasd");
		usr.setActiv(1);
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		System.out.println("Jest ju¿ ID" + usr.getIdUser());
		
		session.save(usr);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println(usr.getIdUser());
	}
	
}
