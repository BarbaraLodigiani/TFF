package twitter.controller;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter.model.Ricerca;
import twitter.model.Risultati;
import twitter.validator.RicercaValidator;
import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import searchengine.Queries;



@Controller
public class BaseController {
	
	private static Queries q = new Queries();
	
	@Autowired
	RicercaValidator ricercaValidator;
	
	
	@RequestMapping(value={"/"}, method = RequestMethod.GET)
	public String hello(Model model) {
		model.addAttribute("FormRicerca", new Ricerca());
		//returns the view name
		return "index";
	}
	
	
	@RequestMapping(value={"/ricerca"}, method = RequestMethod.POST)
	public String ricerca(@ModelAttribute("FormRicerca") Ricerca ricerca, BindingResult result, Model model) throws IOException, ParseException, InvalidTokenOffsetsException, InterruptedException {
//		System.out.println(ricerca.getLocation());
//		System.out.println(ricerca.getInterests());
//		System.out.println(ricerca.getRadioInt());
//		System.out.println(ricerca.getHashtags());
//		System.out.println(ricerca.getRadioHash());
//		System.out.println(ricerca.getAge());
//		System.out.println(ricerca.getGender());
//
		ricercaValidator.validate(ricerca, result);
//		
		if(result.hasErrors()){
			return "index";
		}
		else{
		JSONObject ris = q.search(ricerca.getLocation(), ricerca.getInterests(), ricerca.getRadioInt(), ricerca.getHashtags(), ricerca.getRadioHash(), ricerca.getAge(), ricerca.getGender());
	    model.addAttribute("risultati", ris);
		try {
			model.addAttribute("risSize", ris.getJSONArray("ResultList")
					.length());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return "index";
	}
	
	

}




