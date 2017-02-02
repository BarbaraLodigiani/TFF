package ir;
import geo.Geocode;
import geo.LangDetection;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
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

import analyzer.HashUrlAnalyzer;
import analyzer.TweetsAnalyzer;
import analyzer.TweetsStemAnalyzer;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import uclassify.Uclass;

public class IndexTweet {
	private static ArrayList<User> users;
	private static ArrayList<Status> statuses;
	
	public static void main(String [] args) {
		System.out.println(FileSystems.getDefault().getPath("index", ""));
	}
	
	public static void main2(String[]  args ) {
		GetUsers gu = new GetUsers();
		TweetMap tmap = gu.getTweetMap();
		Uclass uc = new Uclass();
		LangDetection ld = new LangDetection();
		Geocode gc = new Geocode();
		int tweetnum = 0;
		
//		while(gu.isWorking()){
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		
//		System.out.println("++++++++++++++++ indicizzazione ++++++++++++++++++");
//		String tmpTweets = "";
//		String age = "";
//		String gender = "";
//		String location = "";
//		Tweet[] tweet = new Tweet[gu.getMAX_NUM_USER()];
//		
//		HashMap<String, Analyzer> analyzerPerField;
//		PerFieldAnalyzerWrapper aWrapper = null;
//		analyzerPerField= new HashMap<String, Analyzer>();
//
//		String classtmpTweets = null;
//		
//		users = TweetMap.getUserList();
//		for(int j=0; j<users.size();j++){
//			statuses = tmap.getStatusListByUser(users.get(j));
//			for (int i = 0; i<statuses.size(); i++){
//				tmpTweets +=  statuses.get(i).getText() + ".%NUOVOTWEET% ";
//			}
//			classtmpTweets = Pattern.compile("[@＠][a-zA-Z0-9_]+").matcher(tmpTweets).replaceAll(""); 
//			classtmpTweets = Pattern.compile("[#][a-zA-Z0-9_]+").matcher(classtmpTweets).replaceAll(""); 
//		    classtmpTweets = Pattern.compile("%NUOVOTWEET%").matcher(classtmpTweets).replaceAll("");     
//			classtmpTweets =  Pattern.compile(new TweetsAnalyzer().getUrlregex()).matcher(classtmpTweets).replaceAll(""); 	
//	
//			if(ld.getLanguage(classtmpTweets).equals("en")){
//				age = uc.Uclass(classtmpTweets, "Ageanalyzer");	
//				gender = uc.Uclass(classtmpTweets, "GenderAnalyzer_v5");
//				location = gc.getLocation(j);
//				classtmpTweets = "";
//				while(uc.isWorking()){
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			
//				tweet[tweetnum] = new Tweet(users.get(j).getScreenName(), users.get(j).getName(), location, age, gender, users.get(j).getProfileImageURL(), tmpTweets, tmpTweets, tmpTweets);
//					
//				analyzerPerField.put("tweets", new TweetsAnalyzer());
//				analyzerPerField.put("tweetsStem", new TweetsStemAnalyzer());
//				analyzerPerField.put("hashtags", new HashUrlAnalyzer());
//				analyzerPerField.put("age", new WhitespaceAnalyzer());
//				analyzerPerField.put("urlimg", new HashUrlAnalyzer());
//				
//				tweetnum++;
//			}
//
//			tmpTweets = "";
//		}
////		
//		try 
//		{
//
//			//aWrapper = new PerFieldAnalyzerWrapper(new StandardAnalyzer(), analyzerPerField);
//
//			
//			Path path;
//			Directory directory;
//			IndexWriterConfig config;
//			IndexWriter iWriter;
//			
//			path = FileSystems.getDefault().getPath("index", "");
//			directory = FSDirectory.open(path);
//			config = new IndexWriterConfig(aWrapper);
//			iWriter = new IndexWriter(directory,config);
//			for(int i=0; i<tweetnum; i++){
//				iWriter.addDocument(tweet[i].getDocument());
//				System.out.println("indicizzo doc: "+(i+1));
//			}
//			iWriter.close();
//		} 
//		catch (IOException|NullPointerException e) 
//		{
////			e.printStackTrace();
//			System.out.println("error null pointer");
//		}		
			
	}

}
