package medicalclinic.web.controler;

import java.sql.SQLException;

import medicalclinic.model.AppUser;
import medicalclinic.model.UserManagement;

import org.hibernate.HibernateException;
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
	   
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	   public String addUsers(@ModelAttribute("FamilyClinic") AppUser user, 
	   ModelMap model) {
		
		UserManagement um = new UserManagement();
		if(user != null)
		{
			try {
				if(!um.addDoctor(user))
					model.addAttribute("msg", "Wyst¹pi³ b³¹d podczas dodawania u¿ytkownika");
			} catch (HibernateException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			model.addAttribute("msg", "Poprawnie dodano u¿ytkownika");
		}
		
		return "result";
	   }
}
