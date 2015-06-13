package medicalclinic.model;

import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Doctor;
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
		
		String hql = "from DoctorOfficeHours Doctor where doc.id = " + id;

		Query query = session.createQuery(hql);

		result = query.list();
		session.close();

		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Doctor> getDoctorList(){
		List<Doctor> result = null;
		session = sessionFactory.openSession();
		
		String hql = "from Doctor";

		Query query = session.createQuery(hql);

		result = query.list();
		session.close();

		return result;
	}
}