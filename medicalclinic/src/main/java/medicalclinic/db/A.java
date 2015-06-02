package medicalclinic.db;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "A")
public class A {

	@Id
	@Column (name = "ID_A")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDA") 
	@SequenceGenerator(name="IDA", sequenceName = "IDA", allocationSize=1)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "aaa")
	private Set<B> bbb;
		

	public A() {
		
	}
	
	public A(int a, String b, Set<B> aB) {
		this.id = a;
		this.name = b;
		this.bbb = aB;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBbb(Set<B> bbb) {
		this.bbb = bbb;
	}
	
	public Set<B> getBbb() {
		return bbb;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
