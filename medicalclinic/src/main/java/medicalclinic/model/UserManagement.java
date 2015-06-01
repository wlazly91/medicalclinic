package medicalclinic.model;

import java.sql.SQLException;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Doctor;
import medicalclinic.db.Nurse;
import medicalclinic.db.Patient;
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
	 * Metoda zmienie has³o podanego u¿ytkownika 
	 * @param AppUser - u¿ytkownik aplikacji 
	 * @return true - powodzenie / false - niepowodzenie 
	 * */
	public boolean changePassowrd(AppUser appUser)
	{
		Users usr = new Users();
		Doctor doc = new Doctor();
		Patient pat = new Patient();
		Nurse nur = new Nurse();
		List<Users> listUsers = null;
		
		UserManagement um = new UserManagement();
		
		try {
			switch(appUser.getWho()) {								
			case "Doctor":											
				doc.setName(appUser.getName());
				doc.setSurname(appUser.getSurname());
				doc.setSpecjalityName(appUser.getSpecjality());
				usr.setIdDoc(um.getIdDoctor(doc).get(0).getId());
				break;
			case "Patient":
				pat.setName(appUser.getName());
				pat.setSurname(appUser.getSurname());
				pat.setPesel(appUser.getPesel());
				usr.setIdPat(um.getIdPatient(pat).get(0).getIdPatient());
				break;
			case "Nurse":
				nur.setIdNurse(appUser.getIdPerson());
				nur.setName(appUser.getName());
				nur.setSurname(appUser.getSurname());
				usr.setIdNurse(um.getIdNurse(nur).get(0).getIdNurse());
				break;
			case "Admin":
				usr.setIdUser(1);				
			default:
				return false;
			}
			
			listUsers = um.getIdUser(appUser, usr);
			usr.setIdUser(listUsers.get(0).getIdUser());
			usr.setLogin(listUsers.get(0).getLogin());
			usr.setActiv(listUsers.get(0).getActiv());			
			usr.setPassword(appUser.getNewPassword());	
			
			session = sessionFactory.openSession();
			session.beginTransaction();	
			session.update(usr);
			session.getTransaction().commit();
			session.close();
			
			if(usr.getPassword() == appUser.getNewPassword())
				return true;
		} 
		catch (Exception e) {
			
		}
		return false;
	}
	
	
	/**
	 * Metoda pobiera wszytskich u¿ytkowników z tabeli
	 * */
	@SuppressWarnings("unchecked")
	public List<Users> getUsers()
	{
		List<Users> result = null;
		session = sessionFactory.openSession();
		
		String hql = "from Users";

		Query query = session.createQuery(hql);

		
		result = query.list();

		
		return result;
	}
	/**
	 * Metoda zwraca Doctora o podanych parametrach 
	 * @param doc obiekt klasy Doctor
	 * @return result Object reprezentuj¹cy Doctora
	 * */
	@SuppressWarnings("unchecked")
	private List<Doctor> getIdDoctor(Doctor doc)
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
	 * Metoda zwraca pacjenta o podanych parametrach 
	 * @param Patient
	 * @return result Object reprezentuj¹cy Pacjenta
	 * */
	private List<Patient> getIdPatient(Patient pat)
	{
			session = sessionFactory.openSession();
		
			String hql = "from patient where name = '" + pat.getName() + "' And surname = '" + pat.getSurname() + "' And pesel = '" + pat.getPesel() + "'";
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<Patient> results = query.list();
			
			return results;	
	}

	
	/**
	 * Metoda zwraca Pielêgniarke o podanych parametrach 
	 * @param Patient
	 * @return result Object reprezentuj¹cy pielêgniarkê
	 * */
	private List<Nurse> getIdNurse(Nurse nur)
	{
			session = sessionFactory.openSession();
		
			String hql = "from nurse where name = '" + nur.getName() + "' and surname = '" + nur.getSurname() + "'";
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<Nurse> results = query.list();
			
			return results;	
	}

	
	/**
	 * Metoda zwraca Users o podanych parametrach 
	 * @param doc obiekt klasy Users
	 * @return result Object reprezentuj¹cy users
	 * */
	@SuppressWarnings("unused")
	private List<Users> getIdUser(Users usr)
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
	 * Metoda zwraca Users o podanych parametrach 
	 * @param doc obiekt klasy Users
	 * @return result Object reprezentuj¹cy users
	 * */
	private List<Users> getIdUser(AppUser appUser,  Users usr)
	{
		String hql = "";
		if(usr != null)
		{
			session = sessionFactory.openSession();
			switch(appUser.getWho()){
		case "Doctor":											
			hql = "from Users where id_doctor = "+ usr.getIdDoc() +"";
			break;
		case "Patient":
			hql = "from Users where id_nurse = "+ usr.getIdPat() +"";
			break;
		case "Nurse":
			hql = "from Users where id_patient = "+ usr.getIdNurse() +"";
			break;
		case "Admin":
			usr.setIdUser(1);				
		default:
			return null;
		}			
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
	@SuppressWarnings("unused")
	private List<PermissionsUser> getIdPermissions(int idPerm, int idUsr)
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
