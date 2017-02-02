package analyzer;

import java.io.Reader;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.miscellaneous.LengthFilter;
import org.apache.lucene.analysis.pattern.PatternReplaceCharFilter;
import org.apache.lucene.analysis.pattern.PatternReplaceFilter;
import org.apache.lucene.analysis.pattern.PatternTokenizer;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.tartarus.snowball.ext.EnglishStemmer;

public class TweetsAnalyzer extends Analyzer {

	public TweetsAnalyzer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		StandardTokenizer tokenizer = new StandardTokenizer();
		
	    TokenStream filter = new StandardFilter(tokenizer); 
	    filter = new LowerCaseFilter(filter);
	    filter = new StopFilter(filter, StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	    filter = new LengthFilter(filter, 3, Integer.MAX_VALUE);
	    filter = new EnglishPossessiveFilter(filter);
	    
        return new TokenStreamComponents(tokenizer, filter);
        
	}
	

	@Override
	protected Reader initReader(String fieldName, Reader reader) {
     // wrap the Reader in a CharFilter chain.
		if(fieldName.equals("tweets") || fieldName.equals("tweetsStem")){
			return new PatternReplaceCharFilter(Pattern.compile("[@＠][a-zA-Z0-9_]+|%NUOVOTWEET%|"+getUrlregex()), "", reader);
		} 
		else {
			return reader;
		}
	}
 	
 	 
 	public String getUrlregex(){
 		String punctSeq   = "['\"“”‘’]+|[.?!,…]+|[:;]+";	//'anthem'. => ' anthem ' .
	    String entity     = "&(?:amp|lt|gt|quot);";
	    String punctChars = "['\"“”‘’.?!…,:;]"; 
	    String urlStart1  = "(?:https?://|\\bwww\\.)";
	    String commonTLDs = "(?:com|org|edu|gov|net|mil|aero|asia|biz|cat|coop|info|int|jobs|mobi|museum|name|pro|tel|travel|xxx)";
	    String ccTLDs	 = "(?:ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|" +
	    "bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cs|cu|cv|cx|cy|cz|dd|de|dj|dk|dm|do|dz|ec|ee|eg|eh|" +
	    "er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|" +
	    "hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|" +
	    "lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|" +
	    "nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|" +
	    "sl|sm|sn|so|sr|ss|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|" +
	    "va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|za|zm|zw)";	//TODO: remove obscure country domains?
	    String urlStart2  = "\\b(?:[A-Za-z\\d-])+(?:\\.[A-Za-z0-9]+){0,3}\\." + "(?:"+commonTLDs+"|"+ccTLDs+")"+"(?:\\."+ccTLDs+")?(?=\\W|$)";
	    String urlBody    = "(?:[^\\.\\s<>][^\\s<>]*?)?";
	    String urlExtraCrapBeforeEnd = "(?:"+punctChars+"|"+entity+")+?";
	    String urlEnd     = "(?:\\.\\.+|[<>]|\\s|$)";
	    String url        = "(?:"+urlStart1+"|"+urlStart2+")"+urlBody+"(?=(?:"+urlExtraCrapBeforeEnd+")?"+urlEnd+")";
	    return url;
 	}
 	 
 	 
 	 
	

}
