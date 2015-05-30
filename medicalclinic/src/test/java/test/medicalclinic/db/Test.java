package test.medicalclinic.db;

import medicalclinic.db.Address;
import medicalclinic.db.Counties;
import medicalclinic.db.HibernateUtil;
import medicalclinic.db.Locality;
import medicalclinic.db.PostOffice;
import medicalclinic.db.Province;
import medicalclinic.db.Streets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Locality localityPO = new Locality("Boleslaw", 0, 1);
		Locality locality = new Locality("Œwiebodzin", 1, 0);
		PostOffice postOffice = new PostOffice("33-220");
		Streets streets = new Streets("Przyk³adowa");
		Counties counties = new Counties("dabrowski");
		Province province = new Province("ma³opolskie");
		Address adres = new Address(111);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(province);
		session.save(localityPO);
		postOffice.setIdLocality(localityPO);
		session.save(postOffice);
		session.save(streets);
		counties.setIdProvice(province);
		session.save(counties);
		session.save(locality);
		adres.setIdCounties(counties);
		adres.setIdLocality(locality);
		adres.setIdPostoffice(postOffice);
		adres.setIdStrets(streets);
		
		session.save(adres);
		session.getTransaction().commit();
        session.close();
		
        System.out.println("!!! nareszcie cholerstwo posz³o");
	  }

	}