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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** KLasa reprezentuj¹ca tabelê patient
 * (pacjent)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "PATIENT")
public class Patient implements ObjectDB {

	private static final long serialVersionUID = -6276453340015461292L;
	
	@Id
	@Column(name = "ID_PATIENT")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDPATIENT") 
	@SequenceGenerator(name="IDPATIENT", sequenceName = "IDPATIENT", allocationSize=1)
	private int idPatient;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "PESEL")
	private String pesel;
	
	@Column(name = "INSURANCE")
	private String insurance;
	
	@ManyToOne
	@JoinColumn(name = "ID_ADDRESS")
	private Address adres;
	
	@OneToMany(mappedBy="pat")
	private Set<Users> users;
	
	@OneToMany(mappedBy="patient")
	private Set<Visits> visit;
	
	public Patient() {}
	
	public Patient(String nameN, String surnameN, String peselN, String insuranceN, Set<Visits> vis) {
		this.name = nameN;
		this.surname = surnameN;
		this.pesel = peselN;
		this.insurance = insuranceN;
		this.visit = vis;
	}
	
	public void setVisit(Set<Visits> visit) {
		this.visit = visit;
	}
	
	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	
	public void setAdres(Address adres) {
		this.adres = adres;
	}
	
	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Set<Visits> getVisit() {
		return visit;
	}
	
	public Set<Users> getUsers() {
		return users;
	}

	public Address getAdres() {
		return adres;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public String getInsurance() {
		return insurance;
	}

	public String getName() {
		return name;
	}

	public String getPesel() {
		return pesel;
	}

	public String getSurname() {
		return surname;
	}
	
}
