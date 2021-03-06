/**
 * 
 */
package test.medicalclinic.db;

import static org.junit.Assert.*;
import medicalclinic.db.Address;
import medicalclinic.db.Counties;
import medicalclinic.db.HibernateUtil;
import medicalclinic.db.Locality;
import medicalclinic.db.PostOffice;
import medicalclinic.db.Province;
import medicalclinic.db.Streets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

/**
 * @author Lukasz
 *
 */
public class AddressTest {
	
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	/**
	 * Test method for {@link medicalclinic.db.Address#Address()}.
	 */
	@Test
	public void testAddress() {

		Locality localityPO = new Locality("Boleslaw", 0, 1);
		Locality locality = new Locality("Świebodzin", 1, 0);
		PostOffice postOffice = new PostOffice("33-220");
		Streets streets = new Streets("Przykładowa");
		Counties counties = new Counties("dabrowski");
		Province province = new Province("małopolskie");
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
	}

	/**
	 * Test method for {@link medicalclinic.db.Address#Address(int)}.
	 */
	@Test
	public void testAddressInt() {
		fail("Not yet implemented");
	}

}
