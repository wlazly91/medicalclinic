package test.medicalclinic.db;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.A;
import medicalclinic.db.B;
import medicalclinic.db.Users;
import medicalclinic.model.AppUser;
import medicalclinic.model.EncryptionPassword;
import medicalclinic.model.UserManager;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class UsersTest {
	
	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	@Test
	public void testUsers1() throws HibernateException, SQLException {

		UserManager um = new UserManager();
		AppUser appUser = new AppUser();
		
		appUser.setName("Łukasz");
		appUser.setSurname("Kochanek");
		appUser.setSpecjality("Kardiolog");
		appUser.setLogin("lkochan");
		appUser.setPassword(EncryptionPassword.encodePassword("Koch@nek132"));
		appUser.setActive(1);
		appUser.setWho("Doctor");
		
		assertTrue(um.addDoctor(appUser));
	}
	
	@Test
	public void testUsers2() {

		Users usr = new Users();
		
		usr.setLogin("Kubu�asd");
		usr.setPassword("OKAJSasd");
		usr.setActiv(1);
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		assertNotNull("Jest już ID" + usr.getIdUser());
		
		session.save(usr);
		
		session.getTransaction().commit();
		session.close();

	}
	
	
	@Test
	public void testUsers3() {

		UserManager um = new UserManager();
		AppUser appUser = new AppUser();
		AppUser appUser1 = new AppUser();
		
		appUser.setName("Luki");
		appUser.setSurname("Kocha");
		appUser.setSpecjality("Kardiolog");
		appUser.setLogin("lkochan");
		appUser.setPassword("lkochan");
		appUser.setActive(1);
		appUser.setWho("Doctor");
		
		try {
			assertTrue(um.addDoctor(appUser));
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		appUser1.setName("Luki");
		appUser1.setSurname("Kocha");
		appUser1.setSpecjality("Kardiolog");
		appUser1.setNewPassword("Nowehasło");
		appUser1.setWho("Doctor");
		
		try {
			assertTrue("Oczekiwana wrtość TRUE" , um.changePassowrd(appUser1));
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUsers4() {
		
		session = sessionFactory.openSession();
		
		String hql = "from B";

		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<B> result = query.list();		
		
		assertNotNull(result.get(0).getName());
	}
	
	
	@Test
	public void testUsers5() {
		
		AppConfig config = new AppConfig();
		SessionFactory sessionFactory = config.sessionFactory();
		Session session = sessionFactory.openSession();
		A a = new A();
		B b = new B();
	
		session.beginTransaction();
		
		a.setId(1);
		b.setName("Testowe b");
		b.setAaa(a);
		
		session.save(b);
		session.getTransaction().commit();
		

		String hql = "from B where name = 'Testowe b'";

		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<B> result = query.list();		
		session.close();
		assertNotNull(result.get(0).getAaa().getId());
		
	}
	
	@Test
	public void testUsers6() {
		
		session = sessionFactory.openSession();
		
		String hql = "from Users where id_users = 4";

		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users> result = query.list();		
		
		assertNotNull(result.get(0).getDoc().getName() + result.get(0).getDoc().getSurname());
	}
	
	@Test
	public void testUsers7() {
		session = sessionFactory.openSession();
		
		String hql = "from Users";

		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users> result = query.list();		
		
		for (Users users : result) {
			assertNotNull(users.getLogin());
		}
		
		
	}
}
