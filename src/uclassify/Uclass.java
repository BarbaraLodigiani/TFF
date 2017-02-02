package uclassify;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.code.uclassify.client.UClassifyClient;
import com.google.code.uclassify.client.UClassifyClientFactory;
import com.uclassify.api._1.responseschema.ClassInformation;
import com.uclassify.api._1.responseschema.Classification;

public class Uclass {
	
	final UClassifyClientFactory factory;
    final UClassifyClient client;
    private static boolean isWorking = true;

    
    public Uclass() {
        Properties prop = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream("twitter4j.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String key1 = prop.getProperty("uclass.key1");
        String key2 = prop.getProperty("uclass.key2");
        
        factory = UClassifyClientFactory.newInstance(key1, key2);
        client = factory.createUClassifyClient();
    }

	
	public boolean isWorking() {
		return isWorking;
	}
	
	
	public String Uclass(String tweets, String classifier) {
		double prevprob = 0;
		String rangeprob = "";

		Map<String, Classification> classifications = client.classify("uClassify", classifier, Arrays.asList(tweets));
		for(String text : classifications.keySet()) {
			Classification classification = classifications.get(text); 
			
			for (com.uclassify.api._1.responseschema.Class clazz : classification.getClazz()) {
				if(prevprob < clazz.getP())
					rangeprob = clazz.getClassName();
     			prevprob = clazz.getP();
			}
		}
		isWorking = false;
		return rangeprob;
		}
	
}
