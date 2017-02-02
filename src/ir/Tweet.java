package ir;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;

public class Tweet {
	private Document userTweet;
	private TextField tweetsField = null;
	private TextField tweetsStemField = null;
	public Tweet(String user, String name, String location, String age, String sex, String urlimg, String tweets, String tweetsStem, String hashtags){
		tweetsField = new TextField("tweets", tweets, Field.Store.YES);
		tweetsField.setBoost((float) 400000.0);
		
		tweetsStemField = new TextField("tweetsStem", tweetsStem, Field.Store.YES);
		tweetsStemField.setBoost((float) -1000000.0);
		
		userTweet = new Document();
		userTweet.add(new TextField("user", user, Field.Store.YES));
		userTweet.add(new TextField("name", name, Field.Store.YES));
		userTweet.add(new TextField("location", location, Field.Store.YES));
		userTweet.add(new TextField("age", age, Field.Store.YES));
		userTweet.add(new TextField("sex", sex, Field.Store.YES));
		userTweet.add(new TextField("urlimg", urlimg, Field.Store.YES));
		userTweet.add(tweetsField);
		userTweet.add(tweetsStemField);
		userTweet.add(new TextField("hashtags", hashtags, Field.Store.YES));
		
	}
	
	public Document getDocument(){
		return this.userTweet;
	}
	

}
