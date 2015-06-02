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
@Table(name = "B")
public class B {

	@Id
	@Column (name = "ID_B")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDB") 
	@SequenceGenerator(name="IDB", sequenceName = "IDB", allocationSize=1)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
    @JoinColumn(name="ID_A")
	private A aaa;
	
	public B() {}
	
	public B(int a, String b, A aB) {
		this.aaa = aB;
		this.id = a;
		this.name = b;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAaa(A aaa) {
		this.aaa = aaa;
	}
	
	public A getAaa() {
		return aaa;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
