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
/**
 * Klasa reprezentuje lekarzy 
 * @author £ukasz Kochanek
 * @version 1.0
 */

@Entity
@Table(name = "DOCTOR")
public class Doctor implements ObjectDB
{
	private static final long serialVersionUID = 5243155107048347637L;

	@Id	
	@Column(name = "ID_DOCTOR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDDOCTOR") 
	@SequenceGenerator(name="IDDOCTOR", sequenceName = "IDDOCTOR", allocationSize=1)
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "SPECJALITY_NAME")
	private String specjalityName;
	
	@OneToMany(mappedBy="doc")
	private Set<Users> users;
	
	public Doctor() {}
	
	public Doctor(String nameN, String nameS, String specjalityName, Set<Users> a) {
		this.name = nameN;
		this.surname = nameS;
		this.specjalityName = specjalityName;
		this.users = a;
	}
	
//	public void setUsers(Users users) {
//		this.users = users;
//	}
//	
//	public Users getUsers() {
//		return users;
//	}
	
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	
	public Set<Users> getUsers() {
		return users;
	}
		
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setSpecjalityName(String specjalityName) {
		this.specjalityName = specjalityName;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	 
	public String getSpecjalityName() {
		return specjalityName;
	}
}
