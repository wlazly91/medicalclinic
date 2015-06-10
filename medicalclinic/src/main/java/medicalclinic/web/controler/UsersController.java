package medicalclinic.web.controler;

import java.sql.SQLException;
import java.util.List;

import medicalclinic.db.Users;
import medicalclinic.model.AppUser;
import medicalclinic.model.UserManager;
import medicalclinic.model.Who;

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
	public ModelAndView addUsers(@ModelAttribute("FamilyClinic") AppUser user, 
	   ModelMap model) {
		
		ModelAndView modelresult = new ModelAndView("result");
		UserManager um = new UserManager();
		
		if(user != null)
		{
			try {
				if(!um.addDoctor(user))
					modelresult.addObject("msg", "Wystąpił błąd podczas dodawania użytkownika");
				
				modelresult.addObject("msg", "poprawnie dodano użytkownika");
				
			} catch (HibernateException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		return modelresult;
	}
	
	
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public ModelAndView change() {
		return new ModelAndView("change", "command", new Who());
	}
	
	
	
	@RequestMapping(value = "/changePaswword", method = RequestMethod.POST)
	public ModelAndView addUsers(@ModelAttribute("FamilyClinic") Who who, 
	   ModelMap model) {
		ModelAndView modelresult = new ModelAndView("result");
		
		if(who != null)
			model.addAttribute("msg", who.getWho());
		
		return modelresult;
	}
	
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ModelAndView show() {
		
		UserManager um = new UserManager();
		List<Users> users = um.getUsers();
		ModelAndView model = new ModelAndView();
		model.addObject("users", users);
		
		return model;
	}

}
