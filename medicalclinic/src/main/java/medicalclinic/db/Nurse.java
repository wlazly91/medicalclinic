/**
 * 
 */
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
 * Klasa reprezentuj¹ca tabelê nurse
 * (pielêgniarke)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "NURSE")
public class Nurse implements ObjectDB {

	private static final long serialVersionUID = 8681338086538276815L;
	
	@Id
	@Column(name = "ID_NURSE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDNURSE") 
	@SequenceGenerator(name="IDNURSE", sequenceName = "IDNURSE", allocationSize=1)
	private int idNurse;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "POSITION")
	private String position;
	
	@OneToMany(mappedBy="nur")
	private Set<Users> users;
	
	public Nurse() {}
	
	public Nurse(String nameN, String surnameN, String positionN) {
		this.name = nameN;
		this.surname = surnameN;
		this.position = positionN;
	}

	public void setIdNurse(int idNurse) {
		this.idNurse = idNurse;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	
	public int getIdNurse() {
		return idNurse;
	}

	public String getName() {
		return name;
	}
	
	public String getPosition() {
		return position;
	}

	public String getSurname() {
		return surname;
	}
	
	public Set<Users> getUsers() {
		return users;
	}
	
}
