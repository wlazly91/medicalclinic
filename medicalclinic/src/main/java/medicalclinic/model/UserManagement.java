package medicalclinic.model;

import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Doctor;
import medicalclinic.db.Users;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserManagement {

	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	public UserManagement() {}
	
	/**
	 * Metoda Dodaje Doctora do bazy na podstawie Obiektu AppUsers
	 * @param appUser - obiekt klasy AppUsers
	 * @return true - powodzenie operacji/ false - niepowodzenie 
	 * */
	public boolean addDoctor(AppUser appUser)
	{
		Doctor doc = new Doctor();
		UserManagement um = new UserManagement();
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		doc.setName(appUser.getName());
		doc.setSurname(appUser.getSurname());
		doc.setSpecjalityName(appUser.getSpecjality());
		
		session.save(doc);
		session.getTransaction().commit();
		session.close();
		
		appUser.setIdPerson((int)um.getIdDoctor(doc).get(0).getId());
				
		um.addUsers(appUser);
		
		return true;
	}
	/**
	 * Metoda Dodaje Usera do bazy na podstawie Obiektu AppUsers
	 * @param appUser - obiekt klasy AppUsers
	 * @return true - powodzenie operacji/ false - niepowodzenie 
	 * */
	public boolean addUsers(AppUser appUser)
	{
		Users usr = new Users();
		
		session = sessionFactory.openSession();
		session.beginTransaction();

		usr.setLogin(appUser.getLogin());
		usr.setPassword(appUser.getPassword());
		usr.setActiv(appUser.getActive());
		usr.setIdDoc(appUser.getIdPerson());
		
		session.save(usr);
		session.getTransaction().commit();
		session.close();
		
		return true;
	}
	
	/**
	 * Metoda zwraca Doctora o podanych parametrach 
	 * @param doc obiekt klasy Doctor
	 * @return result Object reprezentuj¹cy Doctora
	 * */
	public List<Doctor> getIdDoctor(Doctor doc)
	{
		if(doc.getName() != null && doc.getSurname() != null && doc.getSpecjalityName() != null)
		{
			session = sessionFactory.openSession();
		
			String hql = "from Doctor where name = '"+ doc.getName() +"' AND surname = '"+ doc.getSurname() +"' AND SPECJALITY_NAME = '"+ doc.getSpecjalityName() + "'";
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<Doctor> results = query.list();
			
			return results;
		}
		
		return null;
	}
}
