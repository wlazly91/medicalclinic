package medicalclinic.web.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VisitConroller {

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView selectClinic() {

		ModelAndView modelR = new ModelAndView("register");
		return modelR;
	}
	
//	@RequestMapping(value = "/clinic", method = RequestMethod.POST)
//	public ModelAndView student(@ModelAttribute("FamilyClinic") 
//			   ModelMap model) {
//
//		ModelAndView modelR = new ModelAndView("register");
//
//		return modelR;
//	}
}
