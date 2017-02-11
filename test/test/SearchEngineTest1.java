package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.junit.BeforeClass;
import org.junit.Test;

import searchengine.Queries;
import twitter4j.JSONException;
import twitter4j.JSONObject;

public class SearchEngineTest1 {
	private static Queries q;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		q = new Queries();
	}

	@Test
	public void test() throws IOException, ParseException, InvalidTokenOffsetsException, InterruptedException, JSONException {
		JSONObject result = q.search("","","","","","","");
    	
	       
        assertEquals(result.getJSONArray("ResultList").length() == 0, true); 
	}

}
