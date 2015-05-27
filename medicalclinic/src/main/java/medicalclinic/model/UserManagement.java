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
		
		appUser.setIdPerson(um.getIdDoctor(doc).get(0).getId());

		boolean flag = um.addUsers(appUser);

		if(um.getIdDoctor(doc).get(0).getName().equals(doc.getName()) && um.getIdDoctor(doc).get(0).getSurname().equals(doc.getSurname()) && um.getIdDoctor(doc).get(0).getSpecjalityName().equals(doc.getSpecjalityName()) && flag == true)
			return true;
		
		return false;
	}
	/**
	 * Metoda Dodaje Usera do bazy na podstawie Obiektu AppUsers
	 * @param appUser - obiekt klasy AppUsers
	 * @return true - powodzenie operacji/ false - niepowodzenie 
	 * */
	public boolean addUsers(AppUser appUser)
	{
		Users usr = new Users();
		UserManagement um = new UserManagement();
		session = sessionFactory.openSession();
		session.beginTransaction();

		usr.setLogin(appUser.getLogin());
		usr.setPassword(appUser.getPassword());
		usr.setActiv(appUser.getActive());
		usr.seteMail(appUser.geteMail());
		usr.setPhoneNum(appUser.getPhoneNumber());
				
		switch(appUser.getWho()) {
			case "Dosctor":
				usr.setIdDoc(appUser.getIdPerson());
				break;
			case "Patient":
				usr.setIdPat(appUser.getIdPerson());
				break;
			case "Nurse":
				usr.setIdNurse(appUser.getIdPerson());
				break;
			default:
				break;
		}
		
		session.save(usr);
		session.getTransaction().commit();
		session.close();
		
		if(um.getIdUser(usr).get(0).getLogin().equals(appUser.getLogin()))
			return true;
		
		return false;
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
	
	
	/**
	 * Metoda zwraca Users o podanych parametrach 
	 * @param doc obiekt klasy Users
	 * @return result Object reprezentuj¹cy users
	 * */
	public List<Users> getIdUser(Users doc)
	{
		if(doc.getLogin() != null)
		{
			session = sessionFactory.openSession();
		
			String hql = "from Users where login = '"+ doc.getLogin() +"'";
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<Users> results = query.list();
			
			return results;
		}
		
		return null;
	}
	
	
	/**
	 * Metoda zwraca id uprawnieñ jakie nale¿y nadaæ u¿ytkownikowi 
	 * @param nazwa uprawieñ
	 * @return int ID uprawnieñ w bazie 
	 * */
	public int setPerrmissions(String perm)
	{
		switch(perm) {
			case "Doctor":
				return Permission.DOCTOR.getIdPermision();
			case "Patient":
				return Permission.USER.getIdPermision();
			case "Nurse" :
				return Permission.NURSE.getIdPermision();
			case "Admin":
				return Permission.ADMIN.getIdPermision();
		}
		
		return 0;
	}
}
