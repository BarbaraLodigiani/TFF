package twitter.validator;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
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
		
		if(ricerca.getLocation().isEmpty() && ricerca.getInterests().isEmpty() &&
				ricerca.getHashtags().isEmpty() && ricerca.getAge().isEmpty() && 
				ricerca.getGender().isEmpty()){			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "location.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "interests", "interests.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hashtags", "hashtags.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "age.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "gender.required");
		}
		
		
		if(ricerca.getLocation().matches("[0-9]+")){
			System.out.println("errore");
			errors.rejectValue("location", "locN.required");
			
		}
		
		String[] hashTrim = ricerca.getHashtags().split(" ");
		boolean errore =false;
		System.out.println("hastag: "+ hashTrim[0]);
		for(int i=0; i<hashTrim.length; i++){
			System.out.println(hashTrim[i]);
			if(hashTrim[i].matches("[^a-zA-Z][0-9]*[^a-zA-Z]")){
				errore =true;
			}
		}
		System.out.println("errore"+errore);
		if(errore==true){
			errors.rejectValue("hashtags", "hashN.required");
		}
		
		Directory directory;
		Path path = FileSystems.getDefault().getPath("index");
		try {
			directory = FSDirectory.open(path);
			IndexReader reader = DirectoryReader.open(directory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("index", "index.required");
		}

	}

}
