package medicalclinic.web.controler;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import medicalclinic.model.AppVisits;
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
	public ModelAndView selectClinic( @ModelAttribute("FamilyClinic") AppVisits appVis, ModelMap model) {
		
		VisitsManager vM = new VisitsManager();
		DoctorManager dm = new DoctorManager();
		ModelAndView modelresult = new ModelAndView("visitHours", "command", new AppVisits());
		LinkedHashMap<String, HashMap<java.sql.Date, ArrayList<Time>>> mapOfList = new LinkedHashMap<String, HashMap<java.sql.Date, ArrayList<Time>>>();

		if(appVis != null)
		{
			modelresult.addObject("idDoc", appVis.getId());
			mapOfList = vM.getFreeTerm(appVis.getId());
			modelresult.addObject("mapOfList" , mapOfList);
		}
		
		return modelresult;
	}
	
	@RequestMapping(value = "/visitHours", method = RequestMethod.POST)
	public ModelAndView showDoctorList(@ModelAttribute("FamilyClinic") AppVisits appVis, ModelMap model) {
		
		ModelAndView modelresult = new ModelAndView("visitHours", "command", new AppVisits());

		if(appVis != null)
		{
			modelresult.addObject("msg2" , appVis.getId());
			modelresult.addObject("msg" , appVis.getData());
			modelresult.addObject("msg1" , appVis.getTime());
		}	
		return modelresult;
	}
	
	
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public ModelAndView saveDoctor(@ModelAttribute("FamilyClinic") AppVisits appVis) {
		
		ModelAndView modelresult = new ModelAndView("result");
		VisitsManager vM = new VisitsManager();
		
		if (vM.saveMeVisits(appVis)){
			modelresult.addObject("date", appVis.getData());
			modelresult.addObject("time", appVis.getTime());
		}
		return modelresult;
	}
}
