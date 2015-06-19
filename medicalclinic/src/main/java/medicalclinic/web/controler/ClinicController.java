package medicalclinic.web.controler;

import java.util.List;

import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.model.AppClinic;
import medicalclinic.model.AppVisits;
import medicalclinic.model.ClinicManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClinicController {

	@RequestMapping(value = "/clinic", method = RequestMethod.GET)
	public ModelAndView selectClinic(@ModelAttribute("FamilyClinic") AppClinic appClin, 
			   ModelMap model) {

		ModelAndView modelresult = new ModelAndView("clinic", "command", new AppClinic());
		ClinicManager cM = new ClinicManager();
		List<Clinics> clinic = cM.getClinics();
		modelresult.addObject("clinic" , clinic);
		modelresult.addObject("who", appClin.getWho());
		
		if(appClin.getWho() != null) {
			modelresult.addObject("who", appClin.getWho());
			List<Doctor> doctorList = cM.getDoctorInClinics(appClin.getWho().valueOf(appClin.getWho()));
			modelresult.addObject("doctorList", doctorList);
			modelresult.addObject("Command", new AppVisits());
		}
		
		return modelresult;
	}
	
	@RequestMapping(value = "/clinic", method = RequestMethod.POST)
	public ModelAndView selectClinicPost(@ModelAttribute("FamilyClinic") AppClinic appClin, 
			   ModelMap model) {

		ModelAndView modelresult = new ModelAndView("clinic", "command", new AppClinic());

		return modelresult;
	}
	
}
