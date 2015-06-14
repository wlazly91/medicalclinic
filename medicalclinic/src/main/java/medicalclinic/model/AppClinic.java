package medicalclinic.model;

import java.util.Date;

public class AppClinic {
	
	private String who;
	private int id;
	private Date dateV;
	
	public AppClinic(String whoN, int idN, Date dateVN) {
		this.dateV = dateVN;
		this.id = idN;
		this.who = whoN;
	}
	
	public AppClinic() {}
	
	public void setDateV(Date dateV) {
		this.dateV = dateV;
	}
	
	public void setWho(String who) {
		this.who = who;
	}
	
	public Date getDateV() {
		return dateV;
	}
	
	public String getWho() {
		return who;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
