package test;



import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.annotations.Test;

import searchengine.Queries;
import twitter4j.JSONObject; 
 
public class SearchEngineTest { 
     
    private static Queries q;
     
    @BeforeClass 
    public static void setUpClass() throws Exception { 
    	q = new Queries();

    } 
     
    @AfterClass 
    public static void tearDownClass() throws Exception { 
    } 
    
    /*
     * Metodi privati
     */ 
 
    @Test 
    public void nomeTest() throws IOException, ParseException, InvalidTokenOffsetsException, InterruptedException { 
    	
    	//q.search(ricerca.getLocation(), ricerca.getInterests(), ricerca.getRadioInt(), ricerca.getHashtags(), ricerca.getRadioHash(), ricerca.getAge(), ricerca.getGender());
    	
    	JSONObject result = q.search("","","","","","","");
    	
       
        assertEquals(result.toString().length() == 0, true); 
    } 
}