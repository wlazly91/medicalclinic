package medicalclinic.model;

import java.util.HashMap;

public class FreeTerm {
	
	private HashMap<String, Object> freeTime;
	
	public FreeTerm() {}
	
	public HashMap<String, Object> getFreeTime() {
		return freeTime;
	}
	
	public void setFreeTime(HashMap<String, Object> freeTime) {
		this.freeTime = freeTime;
	}
}
