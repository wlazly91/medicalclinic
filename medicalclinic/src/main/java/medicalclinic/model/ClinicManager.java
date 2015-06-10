package medicalclinic.model;

import java.util.ArrayList;
import java.util.List;

import medicalclinic.config.AppConfig;
import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.db.DoctorOfficeHours;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ClinicManager {

	private String who;
	AppConfig config = new AppConfig();
	SessionFactory sessionFactory = config.sessionFactory();
	Session session;
	
	/**
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public List<DoctorOfficeHours> getDoctorOfficeHours(String name)
	{
		List<DoctorOfficeHours> result = null;
		session = sessionFactory.openSession();
		
		String hql = "from DoctorOfficeHours Clinic where clinic.name = '" + name + "'";
		Query query = session.createQuery(hql);
		
		result = query.list();
		if(result != null)
			return result;

		session.close();		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Clinics> getClinics()
	{
		List<DoctorOfficeHours> result = null;
		List<Clinics> clinicList = new ArrayList<Clinics>();
		session = sessionFactory.openSession();
		
		String hql = "from DoctorOfficeHours";
		Query query = session.createQuery(hql);
		
		result = query.list();
		if(result != null){
			for (int i = 0; i < result.size(); i++) {
				clinicList.add(result.get(i).getClinics());
			}
			
			return clinicList;
		}
		session.close();		
		return clinicList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Doctor> getDoctorInClinics(String name)
	{
		List<DoctorOfficeHours> result = null;
		List<Doctor> clinicList = new ArrayList<Doctor>();
		session = sessionFactory.openSession();
		
		String hql = "from DoctorOfficeHours Clinic where clinic.name = '" + name + "'";
		Query query = session.createQuery(hql);
		
		result = query.list();
		if(result != null){
			for (int i = 0; i < result.size(); i++) {
				clinicList.add(result.get(i).getDoctor());
			}
			
			return clinicList;
		}
		session.close();		
		return clinicList;
	}
		
		
	public void setWho(String who) {
		this.who = who;
	}
	
	public String getWho() {
		return who;
	}
}
