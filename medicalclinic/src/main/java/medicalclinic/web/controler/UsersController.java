package medicalclinic.web.controler;

import medicalclinic.db.Users;

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
	      return new ModelAndView("user", "command", new Users());
	   }
	   
	
	@RequestMapping(value = "/addUsers", method = RequestMethod.POST)
	   public String addStudent(@ModelAttribute("SpringSecurity") Users user, 
	   ModelMap model) {
	      
		if(user != null)
			model.addAttribute("msg", "Uda³o Siê");
	      
		return "result";
	   }
}
