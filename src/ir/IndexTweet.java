package ir;
import geo.Geocode;
import geo.LangDetection;
import twitter.controller.BaseController;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.pattern.PatternReplaceCharFilter;
import org.apache.lucene.analysis.shingle.ShingleAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.eclipse.jdt.internal.compiler.batch.FileSystem;

import analyzer.HashUrlAnalyzer;
import analyzer.TweetsAnalyzer;
import analyzer.TweetsStemAnalyzer;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import uclassify.Uclass;
public class IndexTweet {
	private   ArrayList<User> users;
	private   ArrayList<Status> statuses;
	
	public void crawl() throws IOException{
//		Path pathx;
//		pathx = FileSystems.getDefault().getPath("WebContent", "");
//	    PrintWriter writer = new PrintWriter(new FileOutputStream(pathx+"/resources/crawling.txt"), true);
//	    System.out.println("sto scrivendo");
//	    writer.println("Initializing Crawling process...");
//	    writer.close();
//	    System.out.println("ho finito");
		Path pathx;
		pathx = FileSystems.getDefault().getPath("WebContent", "");
		String timeStamp;
	    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathx+"/resources/crawling.txt", true), StandardCharsets.UTF_8))) {
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	writer.append("Default Crawling process started at: "+timeStamp+"\n\n");
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	writer.append("Crawler started at: "+timeStamp+"\n\n");	    	
	    } 
	    catch (IOException ex) {
	        System.out.println(ex.toString());
	    }
		
		
		GetUsers gu = new GetUsers();
		TweetMap tmap = gu.getTweetMap();
		Uclass uc = new Uclass();
		LangDetection ld = new LangDetection();
		Geocode gc = new Geocode();
		int tweetnum = 0;

		  

//	      ObservableCrawling ov = new ObservableCrawling("");
//	      BaseController to = new BaseController(ov);
//	      ov.addObserver(to);
		
		
		while(gu.isWorking()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//writer.append("++++++++++++++++ indicizzazione ++++++++++++++++++");
//		ov.setValue("++++++++++++++++ indicizzazione ++++++++++++++++++");
		String tmpTweets = "";
		String age = "";
		String gender = "";
		String location = "";
		Tweet[] tweet = new Tweet[gu.getMAX_NUM_USER()];
		
		HashMap<String, Analyzer> analyzerPerField;
		PerFieldAnalyzerWrapper aWrapper = null;
		analyzerPerField= new HashMap<String, Analyzer>();

		String classtmpTweets = null;
		
		users = TweetMap.getUserList();
		for(int j=0; j<users.size();j++){
			statuses = tmap.getStatusListByUser(users.get(j));
			for (int i = 0; i<statuses.size(); i++){
				tmpTweets +=  statuses.get(i).getText() + ".%NUOVOTWEET% ";
			}
			classtmpTweets = Pattern.compile("[@＠][a-zA-Z0-9_]+").matcher(tmpTweets).replaceAll(""); 
			classtmpTweets = Pattern.compile("[#][a-zA-Z0-9_]+").matcher(classtmpTweets).replaceAll(""); 
		    classtmpTweets = Pattern.compile("%NUOVOTWEET%").matcher(classtmpTweets).replaceAll("");     
			classtmpTweets =  Pattern.compile(new TweetsAnalyzer().getUrlregex()).matcher(classtmpTweets).replaceAll(""); 	
	
			if(ld.getLanguage(classtmpTweets).equals("en")){
				age = uc.Uclass(classtmpTweets, "Ageanalyzer");	
				gender = uc.Uclass(classtmpTweets, "GenderAnalyzer_v5");
				location = gc.getLocation(j);
				classtmpTweets = "";
				while(uc.isWorking()){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
				tweet[tweetnum] = new Tweet(users.get(j).getScreenName(), users.get(j).getName(), location, age, gender, users.get(j).getProfileImageURL(), tmpTweets, tmpTweets, tmpTweets);
					
				analyzerPerField.put("tweets", new TweetsAnalyzer());
				analyzerPerField.put("tweetsStem", new TweetsStemAnalyzer());
				analyzerPerField.put("hashtags", new HashUrlAnalyzer());
				analyzerPerField.put("age", new WhitespaceAnalyzer());
				analyzerPerField.put("urlimg", new HashUrlAnalyzer());
				
				tweetnum++;
			}

			tmpTweets = "";
		}
		
		try 
		{

			aWrapper = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), analyzerPerField);

		    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathx+"/resources/crawling.txt", true), StandardCharsets.UTF_8))) {
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	writer.append("Crawler finished at:"+timeStamp+"\n\n");
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	writer.append("Indexing started at:"+timeStamp+"\n\n");	    	
		    } 
		    catch (IOException ex) {
		        System.out.println(ex.toString());
		    }
			
			
			Path path;
			Directory directory;
			IndexWriterConfig config;
			IndexWriter iWriter;
			
			path = FileSystems.getDefault().getPath("index", "");
			directory = FSDirectory.open(path);
			config = new IndexWriterConfig(aWrapper);
			iWriter = new IndexWriter(directory,config);
			for(int i=0; i<tweetnum; i++){
				iWriter.addDocument(tweet[i].getDocument());
			    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathx+"/resources/crawling.txt", true), StandardCharsets.UTF_8))) {
			    	writer.append("Indexing "+(i+1)+" Tweep\n");	    	
			    } 
			    catch (IOException ex) {
			        System.out.println(ex.toString());
			    }			}
			iWriter.close();
		} 
		catch (IOException|NullPointerException e) 
		{
//			e.printStackTrace();
			System.out.println("error null pointer");
		}		
	}
	
}
