package twitter.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import twitter.model.Ricerca;


@Component
public class RicercaValidator implements Validator{
	
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Ricerca.class.equals(arg0);
	}

	@Override
	public void validate(Object o, Errors errors) {
		// TODO Auto-generated method stub
		Ricerca ricerca = (Ricerca) o;
		
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "location.required");
		
		if(ricerca.getLocation().isEmpty() && ricerca.getInterests().isEmpty() &&
				ricerca.getHashtags().isEmpty() && ricerca.getAge().isEmpty() && 
				ricerca.getGender().isEmpty()){			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "location.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "interests", "interests.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hashtags", "hashtags.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "gender.required");
		}
		
		
//		if((!(ricerca.getInterests().isEmpty())) && ricerca.getRadioInt().isEmpty() ){
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "radioInt", "radioInt.required");
//		}
		
	}

}
