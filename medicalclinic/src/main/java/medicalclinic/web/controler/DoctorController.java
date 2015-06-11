package medicalclinic.web.controler;

import java.util.List;

import medicalclinic.db.Doctor;
import medicalclinic.db.DoctorOfficeHours;
import medicalclinic.model.DoctorManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoctorController {

	@RequestMapping(value = "/visitHours", method = RequestMethod.GET)
	public ModelAndView selectClinic(@ModelAttribute("FamilyClinic") Doctor doc, 
			   ModelMap model) {
		DoctorManager dM = new DoctorManager();
		ModelAndView modelresult = new ModelAndView("visitHours", "command", new Doctor());
		List<DoctorOfficeHours> docList = dM.getHoursDoctor(1);
		modelresult.addObject("docList" , docList);
		if(doc.getId() > 0)
			modelresult.addObject("msg", doc.getId());
		
		return modelresult;
	}
}
