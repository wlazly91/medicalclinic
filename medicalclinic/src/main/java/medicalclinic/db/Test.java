package medicalclinic.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		 
		Session session = sessionFactory.openSession();
		//session.beginTransaction();
		Transaction tx = null;
		tx = session.beginTransaction();
      
		Doctor doc = new Doctor();
		doc.setName("cos1");
		doc.setSurname("cos1");
		session.save(doc);	
		tx.commit();
		session.close();
		
		System.out.println("!!! nareszcie cholerstwo posz³o");
	  }

	}