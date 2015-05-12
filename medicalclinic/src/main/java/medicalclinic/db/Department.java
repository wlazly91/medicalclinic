package medicalclinic.db;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTMENT")
public class Department {
 
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDdpt") 
    @SequenceGenerator(name="IDdpt", sequenceName = "IDdpt", allocationSize=1)
    @Column(name="DEPARTMENT_ID")
    private Long departmentId;
     
    @Column(name="DPT_NAME")
    private String departmentName;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="department_id")
    @OrderColumn(name="idx")
    private List<Employee> employees;
    
    public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
    
    public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
    
    public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
    
    public Long getDepartmentId() {
		return departmentId;
	}
    
    public String getDepartmentName() {
		return departmentName;
	}
    
    public List<Employee> getEmployees() {
		return employees;
	}
    
}
