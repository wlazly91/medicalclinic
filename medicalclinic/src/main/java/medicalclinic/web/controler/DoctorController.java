package medicalclinic.web.controler;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;
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
		List<Time> freeTime = new ArrayList<Time>();
				
		if(appClin.getId() > 0){
			freeTime = vM.getFreeTermDoctor(appClin.getId());
			
		}
						
		modelresult.addObject("freeTime" , freeTime);
		
		return modelresult;
	}
}
