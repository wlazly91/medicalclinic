package medicalclinic.model;

import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.DoctorOfficeHours;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DoctorManager {

	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	@SuppressWarnings("unchecked")
	public List<DoctorOfficeHours> getHoursDoctor(int id){
		List<DoctorOfficeHours> result = null;
		session = sessionFactory.openSession();
		
		String hql = "from DoctorOfficeHours Doctor where Doctor.id = " + id;

		Query query = session.createQuery(hql);

		result = query.list();
		session.close();

		return result;
	}
}