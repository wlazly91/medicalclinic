package test.medicalclinic.db;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.A;
import medicalclinic.db.B;
import medicalclinic.db.Users;
import medicalclinic.model.AppUser;
import medicalclinic.model.UserManagement;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

public class UsersTest {
	
	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
//	@Test
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
	
//	@Test
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

	}
	
	
//	@Test
	public void testUsers3() {

		UserManagement um = new UserManagement();
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
		appUser1.setNewPassword("Nowehas³o");
		appUser1.setWho("Doctor");
		
		try {
			assertTrue("Oczekiwana wrtoœæ TRUE" , um.changePassowrd(appUser1));
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
		
		System.out.println(result.get(0).getName());
	}
	
	
//	@Test
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
		System.out.println(result.get(0).getAaa().getId());
		
	}
	
}
