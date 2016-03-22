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
	
	@Column(name = "E_MAIL", nullable = true)
	private String eMail;
	
	@Column(name = "PHONE_NUMBER", nullable = true)
	private String phoneNum;
	
	@Column(name = "LOGIN")
	private String login;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ACTIVE")
	private int activ;
	
	@ManyToOne
    @JoinColumn(name="ID_DOCTOR", nullable = true)
    private Doctor doc;
	
	@ManyToOne
    @JoinColumn(name="ID_NURSE", nullable = true)
    private Nurse nur;
	
	@ManyToOne
    @JoinColumn(name="ID_PATIENT", nullable = true)
    private Patient pat;
	
	@OneToMany(mappedBy="usr")
	private Set<PermissionsUser> usersPerm;
	
	public Users() {}	
	

	public Users(String eMailN, String numberP, String passwordN, String loginN, Doctor doc, int active, int id) {
		this.idUser = id;
		this.eMail = eMailN;
		this.phoneNum = numberP;
		this.password = passwordN;
		this.login = loginN;
		this.doc = doc;
		this.activ = active;
	}
	
	
	public Users(String loginN, String passwordN)
	{
		this.password = passwordN;
		this.login = loginN;
	}
	
	
	public void setUsersPerm(Set<PermissionsUser> usersPerm) {
		this.usersPerm = usersPerm;
	}
	
	public void setDoc(Doctor doc) {
		this.doc = doc;
	}

	public void setActiv(int activ) {
		this.activ = activ;
	}
	
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public void setNur(Nurse nur) {
		this.nur = nur;
	}
	
	public void setPat(Patient pat) {
		this.pat = pat;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public Doctor getDoc() {
		return doc;
	}
	
	public Set<PermissionsUser> getUsersPerm() {
		return usersPerm;
	}
	
	public Patient getPat() {
		return pat;
	}
	
	public int getActiv() {
		return activ;
	}
	
	public String geteMail() {
		return eMail;
	}
	
	
	public Nurse getNur() {
		return nur;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
}
