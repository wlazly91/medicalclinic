package medicalclinic.model;


public class AppUser {

	private String name;
	private String surname;
	private String specjality;
	//-- Patient --//
	private String pesel;
	private String countie;
	private String postOffice;
	private String phoneNumber;
	private String eMail;
	
	private String who;
	private String permisions;
	
	private String login;
	private String password;
	private int active;
	private int idPerson;
	
	public AppUser() {}
	
	
	public int getIdPerson() {
		return idPerson;
	}
	
	public int getActive() {
		return active;
	}
	
	public String getCountie() {
		return countie;
	}
	
	public String geteMail() {
		return eMail;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPesel() {
		return pesel;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getPostOffice() {
		return postOffice;
	}
	
	public String getSpecjality() {
		return specjality;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getWho() {
		return who;
	}
	
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	
	public void setActive(int active) {
		this.active = active;
	}
	
	public void setCountie(String countie) {
		this.countie = countie;
	}
	
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}
	
	public void setSpecjality(String specjality) {
		this.specjality = specjality;
	}
	public void setWho(String who) {
		this.who = who;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
