package medicalclinic.model;

import java.sql.SQLException;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Doctor;
import medicalclinic.db.Nurse;
import medicalclinic.db.Patient;
import medicalclinic.db.Permissions;
import medicalclinic.db.PermissionsUser;
import medicalclinic.db.Users;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * Klasa s�u��ca do zarz�dzania u�ytkownikami
 * @author �.Kochanek
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
			usr.setPassword(EncryptionPassword.encodePassword(appUser.getPassword()));
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
	 * Dodawanie uprawnie� dodawanego u�ytkownika 
	 * @param appUser Obiekt klasy AppUser
	 * @return true - dodawanie zak�nczy�o si� powodzeniem / false - wyst�pi� b��d
	 */
	public boolean addPermission(AppUser appUser) throws HibernateException, SQLException
	{
		PermissionsUser perm = new PermissionsUser();
		UserManagement umManagement = new UserManagement();
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			Users usr = new Users();
			usr.setIdUser(appUser.getIdPerson());
			Permissions permTmp = new Permissions();
			permTmp.setIdPermissions(umManagement.setPerrmissions(appUser.getWho()));
			
			perm.setUsr(usr);
			perm.setPerm(permTmp);
			
			session.save(perm);
			session.getTransaction().commit();
			session.close();
			
			if(perm.getIdPerUser() > 0)
				return true;
		} 
		catch (HibernateException e) {
			System.out.println(e);
			return false;
		}
		return false;
	}
	
	
	/**
	 * Metoda zmienie has�o podanego u�ytkownika 
	 * @param AppUser - u�ytkownik aplikacji 
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
				doc.setId(um.getIdDoctor(doc).get(0).getId());
				break;
			case "Patient":
				pat.setName(appUser.getName());
				pat.setSurname(appUser.getSurname());
				pat.setPesel(appUser.getPesel());
				pat.setIdPatient(um.getIdPatient(pat).get(0).getIdPatient());
				break;
			case "Nurse":
				nur.setIdNurse(appUser.getIdPerson());
				nur.setName(appUser.getName());
				nur.setSurname(appUser.getSurname());
				nur.setIdNurse(um.getIdNurse(nur).get(0).getIdNurse());
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
			usr.setPassword(EncryptionPassword.encodePassword(appUser.getNewPassword()));	
			
			session = sessionFactory.openSession();
			session.beginTransaction();	
			session.update(usr);
			session.getTransaction().commit();
			session.close();
			
			if(EncryptionPassword.encodePassword(usr.getPassword()).equals(EncryptionPassword.encodePassword(appUser.getNewPassword())))
				return true;
		} 
		catch (Exception e) {
			
		}
		return false;
	}
	
	
	/**
	 * Metoda pobiera wszytskich u�ytkownik�w z tabeli
	 * */
	@SuppressWarnings("unchecked")
	public List<Users> getUsers()
	{

		List<Users> result = null;
		session = sessionFactory.openSession();
		
		String hql = "from Users";

		Query query = session.createQuery(hql);

		
		result = query.list();
		session.close();
			
		return result;
	}
	
	
	/**
	 * Metoda pobiera u�ytkownika o podanych loginie
	 * */
	@SuppressWarnings("unchecked")
	public List<Users> getUsers(String login)
	{

		List<Users> result = null;
		session = sessionFactory.openSession();
		
		String hql = "from Users where login = " + login;

		Query query = session.createQuery(hql);

		
		result = query.list();
		session.close();
			
		return result;
	}
	
	/**
	 * Metoda zwraca Doctora o podanych parametrach 
	 * @param doc obiekt klasy Doctor
	 * @return result Object reprezentuj�cy Doctora
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
			
			session.close();
			return results;
		}
		
		return null;
	}


	/**
	 * Metoda zwraca pacjenta o podanych parametrach 
	 * @param Patient
	 * @return result Object reprezentuj�cy Pacjenta
	 * */
	private List<Patient> getIdPatient(Patient pat)
	{
			session = sessionFactory.openSession();
		
			String hql = "from patient where name = '" + pat.getName() + "' And surname = '" + pat.getSurname() + "' And pesel = '" + pat.getPesel() + "'";
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<Patient> results = query.list();
			session.close();
			return results;	
	}

	
	/**
	 * Metoda zwraca Piel�gniarke o podanych parametrach 
	 * @param Patient
	 * @return result Object reprezentuj�cy piel�gniark�
	 * */
	private List<Nurse> getIdNurse(Nurse nur)
	{
			session = sessionFactory.openSession();
		
			String hql = "from nurse where name = '" + nur.getName() + "' and surname = '" + nur.getSurname() + "'";
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<Nurse> results = query.list();
			session.close();
			return results;	
	}

	
	/**
	 * Metoda zwraca Users o podanych parametrach 
	 * @param doc obiekt klasy Users
	 * @return result Object reprezentuj�cy users
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
			session.close();
			return results;
		}
		
		return null;
	}
	

	/**
	 * Metoda zwraca Users o podanych parametrach 
	 * @param doc obiekt klasy Users
	 * @return result Object reprezentuj�cy users
	 * */
	private List<Users> getIdUser(AppUser appUser,  Users usr)
	{
		String hql = "";
		if(usr != null)
		{
			session = sessionFactory.openSession();
			switch(appUser.getWho()){
		case "Doctor":											
			hql = "from Users where id_doctor = "+ usr.getDoc().getId() +"";
			break;
		case "Patient":
			hql = "from Users where id_nurse = "+ usr.getPat().getIdPatient() +"";
			break;
		case "Nurse":
			hql = "from Users where id_patient = "+ usr.getNur().getIdNurse() +"";
			break;
		case "Admin":
			usr.setIdUser(1);				
		default:
			return null;
		}			
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<Users> results = query.list();
			session.close();
			return results;
		}
		
		return null;
	}
	
	
	/**
	 * Metoda zwraca Permissions o podanych parametrach 
	 * @param idPerm ID uprawnie�
	 * @param idUsr ID usera 
	 * @return result Object reprezentuj�cy PermissionsUser
	 * */
	@SuppressWarnings("unused")
	private List<PermissionsUser> getIdPermissions(int idPerm, int idUsr)
	{
			session = sessionFactory.openSession();
		
			String hql = "from PermissionsUser where id_User = "+ idUsr +" AND id_Per = "+ idPerm +"";
			Query query = session.createQuery(hql);
		
			@SuppressWarnings("unchecked")
			List<PermissionsUser> results = query.list();
			session.close();
			return results;	
	}
	
	
	/**
	 * Metoda zwraca id uprawnie� jakie nale�y nada� u�ytkownikowi 
	 * @param nazwa uprawie�
	 * @return int ID uprawnie� w bazie 
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
	 * Metoda w zale�no�ci od podanego parametru ustawia 
	 * w obiekcie Klasy Users (idDictor, idNurse, idPatient)
	 * @param appUsers obiekt klasy AppUser
	 * @param usr obiekt klasy Users w kt�rym b�dziemy ustawia� pole
	 * */
	private void setPersonId(AppUser appUser, Users usr)
	{
		switch(appUser.getWho()) {									//			 Na podstawie zaznaczonej flagi
		case "Doctor":	
			Doctor doctor  = new Doctor();
			doctor.setId(appUser.getIdPerson());					//			 do lekarza, pacjenta lub piel�gniarki
			usr.setDoc(doctor);										//			 do lekarza, pacjenta lub piel�gniarki
			break;
		case "Patient":
			Patient pat = new Patient();
			pat.setIdPatient(appUser.getIdPerson());
			usr.setPat(pat);
			break;
		case "Nurse":	
			Nurse nur = new Nurse();
			nur.setIdNurse(appUser.getIdPerson());
			usr.setNur(nur);
			break;
		default:
			break;
		}
	}	
}
