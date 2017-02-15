package searchengine;

//import gui.GridBagLayoutFormView;
//import gui.GridBagLayoutSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.MultiTermQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLEncoder;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.search.similarities.SimilarityBase;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.BytesRefIterator;

import analyzer.TweetsAnalyzer;
import analyzer.TweetsStemAnalyzer;

import searchengine.Results;
import twitter4j.JSONArray;
import twitter4j.JSONObject;

public class Queries {
	//private static GridBagLayoutSearch layout = new GridBagLayoutSearch();
	//private static GridBagLayoutFormView layoutforms = new GridBagLayoutFormView();
	private static Results layout = new Results();

	public Queries() {
		// TODO Auto-generated constructor stub	
	}
	
	public static ArrayList<String> getAllHashtags() throws IOException{
		ArrayList<String> listOfHash = new ArrayList<String>();
		Path path = FileSystems.getDefault().getPath("index");
		Directory directory = FSDirectory.open(path); 
		IndexReader reader = DirectoryReader.open(directory);
		int count = 0;
	    LuceneDictionary ld = new LuceneDictionary( reader, "hashtags" );
	    BytesRefIterator iterator = ld.getEntryIterator();
	    BytesRef byteRef = null;
	    while (( byteRef = iterator.next()) != null ){
     	   listOfHash.add(byteRef.utf8ToString());   
        }
//	    System.out.println(listOfHash.size());		
		return listOfHash;
	}
	
	public static ArrayList<String> getAllLocations() throws IOException{	
		ArrayList<String> listOfLocation = new ArrayList<String>();
		Path path = FileSystems.getDefault().getPath("index");
		Directory directory = FSDirectory.open(path); 
		IndexReader reader = DirectoryReader.open(directory);
		int count = 0;
	    LuceneDictionary ld = new LuceneDictionary( reader, "location" );
	    BytesRefIterator iterator = ld.getEntryIterator();
	    BytesRef byteRef = null;
	    while (( byteRef = iterator.next()) != null ){
	    	listOfLocation.add(byteRef.utf8ToString());   
        }
//	    System.out.println(listOfLocation.size());
		return listOfLocation;
	}
	
	public static JSONObject search(String getLoc,String getInter,String getInterBool,String getHash,String getHashBool,String getAge,String getGender) throws IOException, ParseException, InvalidTokenOffsetsException, InterruptedException{	
		long startTime = System.currentTimeMillis();
		TweetsAnalyzer analyzerTweets = new TweetsAnalyzer();
		TweetsStemAnalyzer analyzerTweetsStem = new TweetsStemAnalyzer();
	    BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
	    BooleanQuery.Builder booleanQueryScorer = new BooleanQuery.Builder();

	    //QUERY KEYWORDS CON AND
	    if (getInter.length()>0){
	    	String line = getInter;
		    String linesplit[] = line.split(" ");
		    Occur bc  = null;
		    Operator op = null;
		    
		    if (getInterBool.equals("and")){
	    		bc  = Occur.MUST; //l'and
	    		op  = Operator.AND;
		    }
		    else{
		    	bc = Occur.SHOULD;
		    	op = Operator.OR;
		    }
		    
		    BooleanQuery.Builder booleanQuery2 = new BooleanQuery.Builder();
		    BooleanQuery.Builder booleanQueryNot = new BooleanQuery.Builder();
			Query queryx = null;
			QueryParser qp = null;
	        Map<String , Float> boosts = new HashMap<String, Float>();
	        boosts.put("tweetsStem", (float) 0.1);
	        boosts.put("tweets", (float) 10.3);
			HashMap<String, Analyzer> analyzerPerField;
			PerFieldAnalyzerWrapper aWrapper = null;
			analyzerPerField= new HashMap<String, Analyzer>();			
			analyzerPerField.put("tweets", analyzerTweets);
			analyzerPerField.put("tweetsStem", analyzerTweetsStem);
			aWrapper = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), analyzerPerField);
		
		    for(String ls : linesplit){	
				if(ls.substring(0, 1).equals("!")){
					ls = ls.substring(1);
					MatchAllDocsQuery everyDocClause = new MatchAllDocsQuery();
			        MultiFieldQueryParser parser = new MultiFieldQueryParser( new String[] {"tweetsStem","tweets" }, aWrapper, boosts);
			        queryx = parser.parse(ls);
					booleanQueryNot.add(everyDocClause, BooleanClause.Occur.MUST);
					booleanQueryNot.add(queryx, BooleanClause.Occur.MUST_NOT);
				 	booleanQuery2.add(booleanQueryNot.build(), bc);
				} 
				else {				
			        MultiFieldQueryParser parser = new MultiFieldQueryParser( new String[] {"tweetsStem","tweets" }, aWrapper, boosts);
			        queryx = parser.parse(ls);
				 	booleanQuery2.add(queryx, bc);
				}
		    }
		 	booleanQuery.add(booleanQuery2.build(), Occur.MUST);
		 	booleanQueryScorer.add(booleanQuery2.build(), Occur.MUST);
	    }
	  
	    //QUERY HASH
	    if(!getHash.isEmpty()){ 	
	    	String line = getHash;
	    	String newline = "";
	    	Operator bc  = null;
	    	
		    for(String ls : line.split(" ")){
		    	if (ls.charAt(0) != '#'){
		    		newline += "#"+ls+" ";
		    	}
		    	else
		    		newline += ls+" ";
		    }
//    		System.out.println(newline);
	    	
	    	if (getHashBool.equals("and"))
	    		bc = Operator.AND; //l'and
	    	else
	    		bc = Operator.OR;
	    	
		 	QueryParser qp = new QueryParser("hashtags", new StandardAnalyzer());
		 	qp.setDefaultOperator(bc);
		 	Query queryx = qp.parse(newline);
		 	booleanQuery.add(queryx, Occur.MUST);
		 	booleanQueryScorer.add(queryx, Occur.MUST);
	    }
	    
	    //QUERY GENDER
	    if(getGender.length()>0){
	    	
	    	String line = getGender;
		 	QueryParser qp = new QueryParser("sex", new StandardAnalyzer());
		 	Query queryx = qp.parse(line);
		 	booleanQuery.add(queryx, Occur.MUST);  	 	
	    }
	    	    
	    //QUERY LOCATION
	    if(getLoc.length() > 0){	    	
	    	String line = getLoc;
//			String newline = "";
//	    	
//		    for(String ls : line.split(" ")){
//			    perc = (((float)ls.length() - 2)/(float)ls.length());
//			    bdperc = new BigDecimal(perc);
//				str_perc = bdperc.setScale(2, BigDecimal.ROUND_DOWN).toString();
//				newline += ls+" ";
//		    }
	    	
		 	QueryParser qp = new QueryParser("location", new StandardAnalyzer());
		 	qp.setDefaultOperator(Operator.AND);
		 	Query queryx = qp.parse(line);
		 	booleanQuery.add(queryx, Occur.MUST);
	    }
	    
	    //QUERY AGE
	    if(getAge.length()>0){
	    	String line = getAge;
		 	QueryParser qp = new QueryParser("age", new WhitespaceAnalyzer());
		 	Query queryx = qp.parse(line);
		 	booleanQuery.add(queryx, Occur.MUST);
	    } 	   
//	    for (int i = 0; i < 150; ++i) System.out.println();

	Directory directory;
	try {
		Path path = FileSystems.getDefault().getPath("index");
		directory = FSDirectory.open(path);

	IndexReader reader = DirectoryReader.open(directory);
	IndexSearcher searcher = new IndexSearcher(reader);

		
		Sort sort = new Sort(new SortField("tweets", Type.SCORE));
		TopDocs topdocs = searcher.search(booleanQuery.build(), 5000, sort);
		ScoreDoc[] resultsList = topdocs.scoreDocs;
//		for (ScoreDoc d : resultsList)
//			System.out.println(searcher.explain(booleanQuery.build(), d.doc));

		Scorer scorer = new QueryScorer(booleanQueryScorer.build());
		Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter(), new SimpleHTMLEncoder(), scorer);
	    highlighter.setTextFragmenter(new SimpleFragmenter(400));
		ArrayList booksfound = new ArrayList();
		ArrayList<String> tweetsfound = new ArrayList<String>();

		for(int i = 0; i< resultsList.length; i++){
			Document book = searcher.doc(resultsList[i].doc);
			String user= book.getField("user").stringValue();
			String location= book.getField("location").stringValue();
			String age= book.getField("age").stringValue();
			String gender= book.getField("sex").stringValue();			
			String field = "";
			String contents = "";
			
			if(!getLoc.isEmpty()){
				field = "location";
			}
			
			if(!getAge.isEmpty()){
				field = "age";
			}
			
			if(!getGender.isEmpty()){
				contents = book.get("tweetsStem");
				field = "sex";
			}
			
			if(!getHash.isEmpty()){
				field = "hashtags";
				contents = book.get("hashtags");
			}
			
			if(!getInter.isEmpty()){
				contents = book.get("tweetsStem");
				field = "tweetsStem";
			}

			String fragment = null;
			TokenStream tokenStream = null;
//			if(!field.equals("location") && !field.equals("age") && !field.equals("sex")){
//				try{
//					 tokenStream = new TweetsAnalyzer().tokenStream("tweets", new StringReader(book.get("tweets")));
//			         fragment = highlighter.getBestFragment(tokenStream, book.get("tweets"));
//				} catch (NullPointerException e){
//					 tokenStream = new TweetsStemAnalyzer().tokenStream(field, new StringReader(contents));
//				     fragment = highlighter.getBestFragment(tokenStream, contents);				
//				}
//			} 
//			else {
//			    fragment = "<B></B>"+book.get("tweetsStem").substring(0, 400);
//			}
			if(fragment == null){
			    fragment = "<B></B>"+book.get("tweets");
			}
	        String fragments[] = fragment.split("%NUOVOTWEET%");
	        tweetsfound.add(fragment);
//	        int firsttweet = 0;
//	        for (int j = 0; j < fragments.length; j++) {
//	        	if(fragments[j].contains("<B>")&&(firsttweet==0)){
//	        		tweetsfound.add((fragments[j].toString()));
////		       	 	System.out.println(fragments[j].toString());
//		            firsttweet++;
//	        	 }
//	        }
			booksfound.add(book);
			//tweetsfound.add("");
		}
		
	
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);
//		  layout.getSplitresults().setDividerLocation(60);
//		  System.out.println(layout.getSplitresults().getDividerLocation());
//		  layout.getSplitresults().repaint();
//		  layout.getFrame().repaint();
//		  layout.getPanel_view().repaint();
//		  layout.getSearching_panel().repaint();
//		  layout.getScroll_panel().repaint();
//		  System.out.println(layout.getSplitresults() +" splitpane a 60");
		
		return layout.queryResults(booksfound, tweetsfound);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		ArrayList booksfound1 = new ArrayList();
		
		ArrayList<String> tweets1 = new ArrayList();
		tweets1.add("404");
		return layout.queryResults(booksfound1, tweets1) ;
	} 
	
	}
}
