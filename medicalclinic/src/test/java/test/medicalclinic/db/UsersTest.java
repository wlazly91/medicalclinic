package test.medicalclinic.db;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Users;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class UsersTest {
	
	
	@Test
	public void testUsers() {
		Users usr = new Users();
		AppConfig config = new AppConfig();
		SessionFactory sessionFactory = config.sessionFactory();
		Session session;

		
		usr.setIdDoc(1);
		usr.setIdPat(null);
		usr.setIdNurse(null);
		usr.setIdOther(null);
		usr.setPhoneNum("654 654 654");
		usr.seteMail("tsetowy@testowy.com");
		usr.setLogin("testowy");
		usr.setPassword("testowy");
		usr.setActiv(1);
			
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(usr);
		session.getTransaction().commit();
		session.close();
	}

}
