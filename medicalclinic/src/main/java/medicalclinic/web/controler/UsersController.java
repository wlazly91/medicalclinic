package medicalclinic.web.controler;

import medicalclinic.model.AppUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

	  @RequestMapping(value = "/user", method = RequestMethod.GET)
	   public ModelAndView student() {
	      return new ModelAndView("user", "command", new AppUser());
	   }
	   
	
	@RequestMapping(value = "/addUsers", method = RequestMethod.POST)
	   public String addStudent(@ModelAttribute("SpringSecurity") AppUser user, 
	   ModelMap model) {
		
		if(user != null)
		{
			model.addAttribute("name", user.getName());
			model.addAttribute("surname", user.getSurname());
			model.addAttribute("who", user.getWho());
		}
		
		return "result";
	   }
}
