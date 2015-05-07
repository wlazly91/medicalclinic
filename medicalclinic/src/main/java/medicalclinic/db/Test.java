package medicalclinic.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Test {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		 
		Session session = sessionFactory.openSession();
		session.beginTransaction();
//		Transaction tx = null;
//		tx = session.beginTransaction();
		
        Department department = new Department();
        department.setDepartmentName("IT3");
        session.save(department);
         
        Employee emp1 = new Employee("Nina11", "Mayers", "111");
        Employee emp2 = new Employee("Tony11", "Almeida", "222");
 
        emp1.setDepartment(department);
        emp2.setDepartment(department);
         
//        Employee emp3 = new Employee("£ukasz", "Kochanek", "321654");
//        Employee emp4 = new Employee("Asia", "Kochanek", "321654");
        
//        List set1 = new ArrayList<Employee>();
//        set1.add(emp3);
//        set1.add(emp4);
//        department.setEmployees(set1);
//        session.save(department);
        session.save(emp1);
        session.save(emp2);
//		Doctor doc = new Doctor();
//		doc.setName("cos1");
//		doc.setSurname("cos1");
//		session.save(doc);	
//		tx.commit();
        session.getTransaction().commit();
        session.close();
		
		System.out.println("!!! nareszcie cholerstwo posz³o");
	  }

	}