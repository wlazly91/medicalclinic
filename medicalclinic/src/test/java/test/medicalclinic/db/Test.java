package test.medicalclinic.db;

import medicalclinic.db.Address;
import medicalclinic.db.Counties;
import medicalclinic.db.Department;
import medicalclinic.db.Employee;
import medicalclinic.db.HibernateUtil;
import medicalclinic.db.Locality;
import medicalclinic.db.PostOffice;
import medicalclinic.db.Province;
import medicalclinic.db.Streets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Test {

	public static void main(String[] args) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		 
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
////		Transaction tx = null;
////		tx = session.beginTransaction();
//		
//        Department department = new Department();
//        department.setDepartmentName("IT3");
//        session.save(department);
//         
//        Employee emp1 = new Employee("Nina11", "Mayers", "111");
//        Employee emp2 = new Employee("Tony11", "Almeida", "222");
// 
//        emp1.setDepartment(department);
//        emp2.setDepartment(department);
//         
////        Employee emp3 = new Employee("£ukasz", "Kochanek", "321654");
////        Employee emp4 = new Employee("Asia", "Kochanek", "321654");
//        
////        List set1 = new ArrayList<Employee>();
////        set1.add(emp3);
////        set1.add(emp4);
////        department.setEmployees(set1);
////        session.save(department);
//        session.save(emp1);
//        session.save(emp2);		
////		Doctor doc = new Doctor();
////		doc.setName("cos1");
////		doc.setSurname("cos1");
////		session.save(doc);	
////		tx.commit();
//        session.getTransaction().commit();
//        session.close();
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