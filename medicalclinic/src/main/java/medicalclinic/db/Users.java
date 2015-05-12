/**
 * 
 */
package medicalclinic.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * klasa reprezentuj¹ca tabelê USERS
 * u¿ytkowników 
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "USERS")
public class Users implements ObjectDB {

	private static final long serialVersionUID = -7358683619944606503L;
	
	@Id
	@Column(name = "ID_USERS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDUSER") 
	@SequenceGenerator(name="IDUSER", sequenceName = "IDUSER", allocationSize=1)
	private int idUser;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCTOR")
	private Doctor idDoc;
	
	@ManyToOne
	@JoinColumn(name = "ID_PATIENT")
	private Patient idPatient;
	
	@ManyToOne
	@JoinColumn(name = "ID_NURSE")
	private Nurse nurse;
	
	@ManyToOne
	@JoinColumn(name = "ID_OTHER")
	private OtherEmployee idOther;
	
	private Set<Permissions> userRole = new HashSet<Permissions>(0);
	
	@Column(name = "E_MAIL")
	private String eMail;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNum;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "PASSWORD")
	private String password;
	
	
	public Users() {}
	

	public Users(String eMailN, String numberP, String passwordN, String loginN) {
		this.eMail = eMailN;
		this.phoneNum = numberP;
		this.password = passwordN;
		this.login = loginN;
	}
	
	
	public Users(String loginN, String passwordN, Set<Permissions> permissionsN)
	{
		this.password = passwordN;
		this.login = loginN;
		this.userRole = permissionsN;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @param eMail the eMail to set	
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	/**
	 * @param idDoc the idDoc to set
	 */
	public void setIdDoc(Doctor idDoc) {
		this.idDoc = idDoc;
	}
	
	/**
	 * @param idOther the idOther to set
	 */
	public void setIdOther(OtherEmployee idOther) {
		this.idOther = idOther;
	}
	
	/**
	 * @param idPatient the idPatient to set
	 */
	public void setIdPatient(Patient idPatient) {
		this.idPatient = idPatient;
	}
	
	public void setUserRole(Set<Permissions> userRole) {
		this.userRole = userRole;
	}
	
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	/**
	 * @param nurse the nurse to set
	 */
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}
	
	/**
	 * @return the idDoc
	 */
	public Doctor getIdDoc() {
		return idDoc;
	}
	
	/**
	 * @return the idOther
	 */
	public OtherEmployee getIdOther() {
		return idOther;
	}
	
	/**
	 * @return the idPatient
	 */
	public Patient getIdPatient() {
		return idPatient;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Permissions> getUserRole() {
		return userRole;
	}
	
	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}
	
	
	/**
	 * @return the nurse
	 */
	public Nurse getNurse() {
		return nurse;
	}
	
	/**
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	
}
