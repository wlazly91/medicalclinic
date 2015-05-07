package medicalclinic.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
 
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDemployee") 
	@SequenceGenerator(name="IDemployee", sequenceName = "IDemployee", allocationSize=1)
    @Column(name="employee_id")
    private Long employeeId;
     
    @Column(name="firstname")
    private String firstname;
     
    @Column(name="lastname")
    private String lastname;
    
    @Column(name="cell_phone")
    private String cellphone;
 
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;
     
    public Employee() {
         
    }
     
    public Employee(String firstname, String lastname, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.cellphone = phone;
    }
 
    public String getCellphone() {
		return cellphone;
	}
    
    public Department getDepartment() {
		return department;
	}
    
    public Long getEmployeeId() {
		return employeeId;
	}
    
    public String getFirstname() {
		return firstname;
	}
    
    public String getLastname() {
		return lastname;
	}
    
    public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
    
    public void setDepartment(Department department) {
		this.department = department;
	}
    
    public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
    
    public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
    
    public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
