package medicalclinic.model;

import java.util.Date;

public class DateInfo {

	/**
	 *      0 = Sunday, 
            1 = Monday, 
            2 = Tuesday, 
            3 = Wednesday, 
            4 = Thursday, 
            5 = Friday, 
            6 = Saturday
	 * */
	@SuppressWarnings("deprecation")
	public static String getDayOfWeekInDate(Date date){
		String nameDay = "";
		
		switch(date.getDay()) {
		case 0:
			return nameDay = "Sunday";
		case 1:
			return nameDay = "Monday";
		case 2:
			return nameDay = "Tuesday";
		case 3:
			return nameDay = "Wednesday";
		case 4:
			return nameDay = "Thursday";
		case 5:
			return nameDay = "Friday";	
		case 6:
			return nameDay = "Saturday";
	}
		return nameDay;
	}
}
