package test.medicalclinic.db;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Doctor;
import medicalclinic.model.AppUser;
import medicalclinic.model.UserManagement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
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
	public void testUsers1() {

		UserManagement um = new UserManagement();
		AppUser appUser = new AppUser();
		
		
		appUser.setName("£ukasz1");
		appUser.setSurname("Kochanek");
		appUser.setSpecjality("Kardiolog");
		appUser.setLogin("lkochan1");
		appUser.setPassword("lkochan");
		appUser.setActive(1);
		
		um.addDoctor(appUser);
	}
	
//	@Test
	public void testUsers2() {

		UserManagement um = new UserManagement();
		AppUser appUser = new AppUser();
		
		
		appUser.setName("£ukasz");
		appUser.setSurname("Kochanek");
		appUser.setSpecjality("Kardiolog");
		appUser.setLogin("lkochan");
		appUser.setPassword("lkochan");
		appUser.setActive(1);
		appUser.setIdPerson(47);
		
		um.addUsers(appUser);
	}
	
}
