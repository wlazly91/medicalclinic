/**
 * 
 */
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
	
	@JoinColumn(name = "ID_PATIENT", nullable = true)
	@Column(name = "ID_PATIENT")
	private Integer idPat;

	@JoinColumn(name = "ID_NURSE", nullable = true)
	@Column(name = "ID_NURSE")
	private Integer idNurse;
	
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
    @JoinColumn(name="ID_DOCTOR")
    private Doctor doc;
	
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
	
	public Doctor getDoc() {
		return doc;
	}
	
	public void setDoc(Doctor doc) {
		this.doc = doc;
	}
	
//	public void setPermission(Set<Permissions> permission) {
//		this.permission = permission;
//	}
//	
//	public Set<Permissions> getPermission() {
//		return permission;
//	}
	
//	public void setStreets(String streets) {
//		this.streets = streets;
//	}
	
	public void setActiv(int activ) {
		this.activ = activ;
	}
	
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public void setIdDoc(Integer idDoc) {
//		this.idDoc = idDoc;
	}
	
	public void setIdNurse(Integer idNurse) {
		this.idNurse = idNurse;
	}
	
//	public void setIdOther(Integer idOther) {
//		this.idOther = idOther;
//	}
	
	public void setIdPat(Integer idPat) {
		this.idPat = idPat;
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
	
//	public String getStreets() {
//		return streets;
//	}
	
	public int getActiv() {
		return activ;
	}
	
	public String geteMail() {
		return eMail;
	}
	
	public Integer getIdDoc() {
//		return idDoc;
		return 10;
	}
	
	public Integer getIdNurse() {
		return idNurse;
	}
	
//	public Integer getIdOther() {
//		return idOther;
//	}
	
	public Integer getIdPat() {
		return idPat;
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
