package twitter.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import twitter.model.Ricerca;

public class RicercaValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Ricerca.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object o, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "location.required");
		
	}

}
