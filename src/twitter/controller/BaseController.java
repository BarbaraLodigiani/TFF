package twitter.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter.model.Ricerca;
import twitter.validator.RicercaValidator;



@Controller
public class BaseController {
	
	@Autowired
	private RicercaValidator ricercaValidator;
	
	
	@RequestMapping(value={"/"}, method = RequestMethod.GET, produces = "text/plain;charset = UTF-8")
	public String hello(Model model) {
		Ricerca ricerca =new Ricerca();
		model.addAttribute("FormRicerca", ricerca);
		//returns the view name
		return "index";
	}
	
	
	@RequestMapping(value={"/ricerca"}, method = RequestMethod.POST, produces = "text/plain;charset = UTF-8")
	public String ricerca(@ModelAttribute("FormRicerca") Ricerca ricerca, BindingResult bind, Model model) {
		System.out.println(ricerca.getLocation());
		System.out.println(ricerca.getInterests());
		System.out.println(ricerca.getRadioInt());
		System.out.println(ricerca.getHashtags());
		System.out.println(ricerca.getRadioHash());
		System.out.println(ricerca.getAge());
		System.out.println(ricerca.getGender());

		ricercaValidator.validate(ricerca, bind);
		
		if(bind.hasErrors()){
			return "index";
		}

		//returns the view name
		return "index";
	}
	
}




