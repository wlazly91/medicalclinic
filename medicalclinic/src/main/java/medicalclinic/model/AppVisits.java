package medicalclinic.model;

import java.sql.Date;
import java.sql.Time;

public class AppVisits {

	private int id;
	private String nameDoctor;
	private String surnameDoctor;
	
	private String hours;
	private Date data;
	private Time time;
	
	
	private String clinicName;
	
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	public void setTime(Time time) {
		this.time = time;
	}
	
	public int getId() {
		return id;
	}
	
	public String getClinicName() {
		return clinicName;
	}
	
	public java.sql.Date getData() {
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
	
	public Time getTime() {
		return time;
	}
}
