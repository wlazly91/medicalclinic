package test.medicalclinic.db;

import java.sql.SQLException;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Doctor;
import medicalclinic.model.AppUser;
import medicalclinic.model.UserManagement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class UsersTest {
	
	
//	@Test
	public void testUsers() {
		AppConfig config = new AppConfig();
		SessionFactory sessionFactory = config.sessionFactory();
		Session session;

		UserManagement um = new UserManagement();
		Doctor doc = new Doctor();
		
		doc.setName("£ukasz");
		doc.setSurname("Kochanek");
		doc.setSpecjalityName("Kardiolog");
			
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(doc);
		session.getTransaction().commit();
		session.close();
//		Assert.assertNotNull(um.getIdDoctor(doc));
		System.out.println(um.getIdDoctor(doc).get(0).getName());
	}

	
	@Test
	public void testUsers1() throws HibernateException, SQLException {

		UserManagement um = new UserManagement();
		AppUser appUser = new AppUser();
		
		appUser.setName("Testowy");
		appUser.setSurname("Testowy");
		appUser.setSpecjality("Testowy");
		appUser.setLogin("Testowy");
		appUser.setPassword("Testowe");
		appUser.setActive(1);
		appUser.setWho("Doctor");
		
//		um.addPermission(appUser);
		
		um.addDoctor(appUser);
	}
	
	@Test
	public void testUsers2() {

		
	}
	
}
