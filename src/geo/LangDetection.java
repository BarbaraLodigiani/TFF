package geo;

import java.io.File;
import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;


public class LangDetection {

	public static String getLanguage(String text) {
		// TODO Auto-generated method stub
		File file = new File("./profiles/");
		try {
		 	if(DetectorFactory.getLangList().isEmpty())
				DetectorFactory.loadProfile(file);
			Detector detector = DetectorFactory.create(); 
			detector.append(text);
			String lang = detector.detect();
			return lang;


		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";

		}


	}

}
