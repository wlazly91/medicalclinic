package medicalclinic.model;

import java.sql.SQLException;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Doctor;
import medicalclinic.db.PermissionsUser;
import medicalclinic.db.Users;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * Klasa s³u¿¹ca do zarz¹dzania u¿ytkownikami
 * @author £.Kochanek
 * @version 1.0
 * */
public class UserManagement {

	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	public UserManagement() {}
	
	/**
	 * Metoda Dodaje Doctora do bazy na podstawie Obiektu AppUsers
	 * @param appUser - obiekt klasy AppUsers
	 * @return true - powodzenie operacji/ false - niepowodzenie 
	 * @throws SQLException 
	 * @throws HibernateException 
	 * */
	public boolean addDoctor(AppUser appUser) throws HibernateException, SQLException
	{
		Doctor doc = new Doctor();
		UserManagement umManagement = new UserManagement();
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
		
			doc.setName(appUser.getName());
			doc.setSurname(appUser.getSurname());
			doc.setSpecjalityName(appUser.getSpecjality());

			session.save(doc);
			session.getTransaction().commit();
			session.close();
			
			appUser.setIdPerson(doc.getId());
			
			boolean flag = umManagement.addUsers(appUser);

			if(doc.getId() > 0 && flag == true)
				return true;
		
		}
		catch (HibernateException e) {
			System.out.println(e);
			return false;
		}
		return false;
	}
	
	
	/**
	 * Metoda Dodaje Usera do bazy na podstawie Obiektu AppUsers
	 * @param appUser - obiekt klasy AppUsers
	 * @return true - powodzenie operacji/ false - niepowodzenie 
	 * @throws SQLException 
	 * */
	public boolean addUsers(AppUser appUser) throws HibernateException, SQLException
	{
		Users usr = new Users();
		UserManagement umManagement = new UserManagement();
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			usr.setLogin(appUser.getLogin());
			usr.setPassword(appUser.getPassword());
			usr.setActiv(appUser.getActive());
			usr.seteMail(appUser.geteMail());
			usr.setPhoneNum(appUser.getPhoneNumber());
			umManagement.setPersonId(appUser, usr);
		
			session.save(usr);
			session.getTransaction().commit();
			session.close();

			appUser.setIdPerson(usr.getIdUser());
			
			boolean flag = umManagement.addPermission(appUser);
			
			if(usr.getIdUser() > 0 && flag == true)
				return true;
		}
		catch (HibernateException e) {
			System.out.println(e);
			return false;
		}
		return false;
	}
	
	/**
	 * Dodawanie uprawnieñ dodawanego u¿ytkownika 
	 * @param appUser Obiekt klasy AppUser
	 * @return true - dodawanie zakónczy³o siê powodzeniem / false - wyst¹pi³ b³¹d
	 */
	public boolean addPermission(AppUser appUser) throws HibernateException, SQLException
	{
		PermissionsUser perm = new PermissionsUser();
		UserManagement umManagement = new UserManagement();
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			perm.setIdUser(appUser.getIdPerson());
			perm.setIdPer(umManagement.setPerrmissions(appUser.getWho()));
			
			session.save(perm);
			session.getTransaction().commit();
			session.close();
			
			if(perm.getIdPer() > 0)
				return true;
		} 
		catch (HibernateException e) {
			System.out.println(e);
			return false;
		}
		return false;
	}
	
	
	/**
	 * Metoda zwraca Doctora o podanych parametrach 
	 * @param doc obiekt klasy Doctor
	 * @return result Object reprezentuj¹cy Doctora
	 * */
	@SuppressWarnings("unchecked")
	public List<Doctor> getIdDoctor(Doctor doc)
	{
		if(doc.getName() != null && doc.getSurname() != null && doc.getSpecjalityName() != null)
		{
			session = sessionFactory.openSession();
		
			String hql = "from Doctor where name = '"+ doc.getName() +"' AND surname = '"+ doc.getSurname() +"' AND SPECJALITY_NAME = '"+ doc.getSpecjalityName() + "'";
			Query query = session.createQuery(hql);
		
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
	public List<Users> getIdUser(Users usr)
	{
		if(usr.getLogin() != null)
		{
			session = sessionFactory.openSession();
		
			String hql = "from Users where login = '"+ usr.getLogin() +"'";
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<Users> results = query.list();
			
			return results;
		}
		
		return null;
	}
	

	/**
	 * Metoda zwraca Permissions o podanych parametrach 
	 * @param idPerm ID uprawnieñ
	 * @param idUsr ID usera 
	 * @return result Object reprezentuj¹cy PermissionsUser
	 * */
	public List<PermissionsUser> getIdPermissions(int idPerm, int idUsr)
	{
			session = sessionFactory.openSession();
		
			String hql = "from PermissionsUser where id_User = "+ idUsr +" AND id_Per = "+ idPerm +"";
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<PermissionsUser> results = query.list();
			
			return results;	
	}

	/**
	 * Metoda zwraca id uprawnieñ jakie nale¿y nadaæ u¿ytkownikowi 
	 * @param nazwa uprawieñ
	 * @return int ID uprawnieñ w bazie 
	 * */
	private int setPerrmissions(String perm)
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
	
	/**
	 * Metoda w zale¿noœci od podanego parametru ustawia 
	 * w obiekcie Klasy Users (idDictor, idNurse, idPatient)
	 * @param appUsers obiekt klasy AppUser
	 * @param usr obiekt klasy Users w którym bêdziemy ustawiaæ pole
	 * */
	private void setPersonId(AppUser appUser, Users usr)
	{
		switch(appUser.getWho()) {								//			 Na podstawie zaznaczonej flagi
		case "Doctor":											//			 do lekarza, pacjenta lub pielêgniarki
			usr.setIdDoc(appUser.getIdPerson());				//			 do lekarza, pacjenta lub pielêgniarki
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
	}
}
