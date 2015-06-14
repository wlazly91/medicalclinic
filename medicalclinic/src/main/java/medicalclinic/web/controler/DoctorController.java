package medicalclinic.web.controler;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import medicalclinic.db.DoctorOfficeHours;
import medicalclinic.model.AppClinic;
import medicalclinic.model.DoctorManager;
import medicalclinic.model.VisitsManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoctorController {

	@RequestMapping(value = "/visitHours", method = RequestMethod.GET)
	public ModelAndView selectClinic(@RequestParam(value = "valueMapDate") Date valueMap, @ModelAttribute("FamilyClinic") AppClinic appClin, ModelMap model) {
		DoctorManager dM = new DoctorManager();
		
		ModelAndView modelresult = new ModelAndView("visitHours", "command", new AppClinic());
				
		List<DoctorOfficeHours> docList = dM.getHoursDoctor(appClin.getId());
		
		modelresult.addObject("docList" , docList);
		
		if(valueMap != null)
			modelresult.addObject("msg", valueMap);
		
		return modelresult;
	}
	
	@RequestMapping(value = "/visitHours", method = RequestMethod.POST)
	public ModelAndView showDoctorList(@ModelAttribute("FamilyClinic") AppClinic appClin, ModelMap model, Date valueMap) {
		
		ModelAndView modelresult = new ModelAndView("visitHours", "command", new AppClinic());
		
		VisitsManager vM = new VisitsManager();
		LinkedHashMap<String, HashMap<java.sql.Date, ArrayList<Time>>> mapOfList = new LinkedHashMap<String, HashMap<java.sql.Date, ArrayList<Time>>>();
		mapOfList = vM.getFreeTerm(appClin.getId());
		
		if(valueMap != null)
			modelresult.addObject("msg", valueMap);
		
		modelresult.addObject("mapOfList" , mapOfList);
		
		return modelresult;
	}
}
