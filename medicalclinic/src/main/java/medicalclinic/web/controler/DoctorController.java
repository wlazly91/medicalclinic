package medicalclinic.web.controler;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import medicalclinic.db.Doctor;
import medicalclinic.db.DoctorOfficeHours;
import medicalclinic.model.DoctorManager;
import medicalclinic.model.VisitsManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoctorController {

	@RequestMapping(value = "/visitHours", method = RequestMethod.GET)
	public ModelAndView selectClinic(@ModelAttribute("FamilyClinic") Doctor appClin, ModelMap model) {
		DoctorManager dM = new DoctorManager();
		ModelAndView modelresult = new ModelAndView("visitHours", "command", new Doctor());
		List<DoctorOfficeHours> docList = dM.getHoursDoctor(appClin.getId());
		
		modelresult.addObject("docList" , docList);
		return modelresult;
	}
	
	@RequestMapping(value = "/visitHours", method = RequestMethod.POST)
	public ModelAndView showDoctorList(@ModelAttribute("FamilyClinic") Doctor appClin, ModelMap model) {
		
		ModelAndView modelresult = new ModelAndView("visitHours", "command", new Doctor());
		
		VisitsManager vM = new VisitsManager();
		HashMap<String, HashMap<java.sql.Date, ArrayList<Time>>> mapOfList = new HashMap<String, HashMap<java.sql.Date, ArrayList<Time>>>();
		mapOfList = vM.getFreeTerm(321);
//		HashMap<String, Integer> freeTime = new HashMap<String, Integer>();
//		HashMap<String, ArrayList<Integer>> mapOfList = new HashMap<String, ArrayList<Integer>>();
//		
//		ArrayList<Integer> a = new ArrayList<Integer>();
//		
//		a.add(100);
//		a.add(300);
//		a.add(500);
//
//		mapOfList.put("Udało", a);
		
		modelresult.addObject("mapOfList" , mapOfList);
						
		
		
		return modelresult;
	}
}
