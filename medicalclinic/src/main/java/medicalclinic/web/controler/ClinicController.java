package medicalclinic.web.controler;

import java.util.List;

import medicalclinic.db.Clinics;
import medicalclinic.db.Doctor;
import medicalclinic.model.AppClinic;
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
		
		if(appClin.getWho() != null) {
			List<Doctor> doctorList = cM.getDoctorInClinics(appClin.getWho());
			modelresult.addObject("doctorList", doctorList);
		}
		
		return modelresult;
	}
	
	@RequestMapping(value = "/clinic", method = RequestMethod.POST)
	public ModelAndView student(@ModelAttribute("FamilyClinic") AppClinic appClin, 
			   ModelMap model) {

		ModelAndView modelresult = new ModelAndView("clinic", "command", new AppClinic());

		return modelresult;
	}
	
}
