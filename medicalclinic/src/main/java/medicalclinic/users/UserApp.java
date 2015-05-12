package medicalclinic.users;

import java.util.ArrayList;
import java.util.List;

import medicalclinic.db.HibernateUtil;
import medicalclinic.db.ObjectDB;
import medicalclinic.db.Users;

import org.hibernate.SessionFactory;

public class UserApp implements ObjectDB {

	private static final long serialVersionUID = 8928898228573057019L;
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@SuppressWarnings("unchecked")
	public Users findByUserName(String userName){
		
		List<Users> users = new ArrayList<Users>();
		users = sessionFactory.getCurrentSession().createQuery("from users where username = ?").setParameter(0, userName).list();
		
		if(users.size() > 0)
			return users.get(0);
		
		return null;
	}
}
