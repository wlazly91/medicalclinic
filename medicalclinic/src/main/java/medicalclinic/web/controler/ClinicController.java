package medicalclinic.web.controler;

import medicalclinic.model.AppUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClinicController {

	@RequestMapping(value = "/clinic", method = RequestMethod.GET)
	public ModelAndView selectClinic(@ModelAttribute("FamilyClinic") String user, 
			   ModelMap model) {
		
		ModelAndView modelresult = new ModelAndView("clinic", "command", new AppUser());

		return modelresult;
	}
	
	@RequestMapping(value = "/clinic", method = RequestMethod.POST)
	public ModelAndView student(@ModelAttribute("FamilyClinic") AppUser user, 
			   ModelMap model) {
		
		ModelAndView modelresult = new ModelAndView("clinic", "command", new AppUser());
		
		modelresult.addObject("msg", user.getLogin());

		
		return modelresult;
	}
	
}
