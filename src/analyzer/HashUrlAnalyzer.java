package analyzer;

import ir.Tweet;

import java.util.List;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader; 
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.charfilter.HTMLStripCharFilter;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.miscellaneous.LengthFilter;
import org.apache.lucene.analysis.pattern.PatternReplaceCharFilter;
import org.apache.lucene.analysis.pattern.PatternReplaceFilter;
import org.apache.lucene.analysis.pattern.PatternTokenizer;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.queryparser.classic.CharStream;
import org.apache.lucene.util.Version;
import org.tartarus.snowball.ext.EnglishStemmer;
import org.tartarus.snowball.ext.PorterStemmer;

public class HashUrlAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		
		if(fieldName.equals("hashtags")){
			PatternTokenizer tokenizer = new PatternTokenizer(Pattern.compile("#[a-zA-Z0-9_]+"), 0);
		    TokenStream filter = new LowerCaseFilter(tokenizer); 
		    filter = new PatternReplaceFilter(filter, Pattern.compile("#"),"", true);
	        return new TokenStreamComponents(tokenizer, filter);

		} else if(fieldName.equals("urlimg")) {
			KeywordTokenizer tokenizer = new KeywordTokenizer();
	        return new TokenStreamComponents(tokenizer);

		} else {
			
	        return null;
			
		}
        
	}


}