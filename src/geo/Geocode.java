package geo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;

public class Geocode {
	
	public static String API_key;
	private static ArrayList<String> userLocations = new ArrayList<String>();
	
	public Geocode() {
        
        Properties prop = new Properties();
        
        InputStream is = getClass().getClassLoader().getResourceAsStream("twitter4j.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        String key = prop.getProperty("map.key");
        
        this.API_key = key;
    }

	public boolean checkLocation(String search) {
		// TODO Auto-generated method stub		
		GeoApiContext context = new GeoApiContext().setApiKey(API_key);
		
		GeocodingResult[] results = null;
		
		boolean check = false;
		
//		String search = "Ohio";
		
		try {
			results = GeocodingApi.geocode(context, search).await();
			
			if(results.length > 0) 
			{
//				System.out.println(results[0].formattedAddress);
				
//				if( results[0].types[0].name().equals(AddressComponentType.LOCALITY.name()) && results[0].types[1].name().equals(AddressComponentType.POLITICAL.name()) )
				if( containPolitical(results[0].types) ){
					userLocations.add(results[0].formattedAddress);
					check = true;
					
				}
//					System.out.println(" = = SALVO ! = =");
				else
					check = false;
//					System.out.println("* ok, ma no *");
			}
			else
				check = false;
//				System.out.println("* ma non esiste proprio! *");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
			
		

	}
	
	private static boolean containPolitical(AddressType[] types)
	{
		for(int i=0; i<types.length; i++)
			if(types[i].name().equals(AddressComponentType.POLITICAL.name()))
				return true;
		
		return false;
	}
	
	

	public String getLocation(int locationIndex) {
		// TODO Auto-generated method stub

		return userLocations.get(locationIndex);
			
		
	}

}
