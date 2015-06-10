package medicalclinic.model;

import java.sql.Date;

public class AppVisits {

	private String nameDoctor;
	private String surnameDoctor;
	
	private String hours;
	private Date data;
	
	private String clinicName;
	
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public void setHours(String hours) {
		this.hours = hours;
	}
	
	public void setNameDoctor(String nameDoctor) {
		this.nameDoctor = nameDoctor;
	}
	
	public void setSurnameDoctor(String surnameDoctor) {
		this.surnameDoctor = surnameDoctor;
	}
	
	public String getClinicName() {
		return clinicName;
	}
	
	public Date getData() {
		return data;
	}
	
	public String getHours() {
		return hours;
	}
	
	public String getNameDoctor() {
		return nameDoctor;
	}
	
	public String getSurnameDoctor() {
		return surnameDoctor;
	}
}
