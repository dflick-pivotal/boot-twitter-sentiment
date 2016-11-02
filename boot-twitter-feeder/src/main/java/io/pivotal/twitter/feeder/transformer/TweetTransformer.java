package io.pivotal.twitter.feeder.transformer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.util.StringUtils;

import com.twitter.Extractor;

import io.pivotal.twitter.feeder.service.NLP;
import io.pivotal.twitter.model.TweetData;

public class TweetTransformer {
	
	@Autowired
	NLP nlp;
	

	public TweetData transform(Tweet t) {
		TweetData td = new TweetData();
		td.setCreatedAt((t.getCreatedAt()).toString());
		td.setTweet(t.getText());
		td.setSentiment(analyzeSentiment(t.getText()));
		return td;
	}

	private int analyzeSentiment(String text)
	{
		List<String> list;
		Extractor extractor = new Extractor();
		
		String temp = text;

		// Clear Tweet for sentiment analytics
		list = extractor.extractURLs(temp);	
		for (String i : list) {
			temp = StringUtils.delete(temp, i);
		}
		
		list = extractor.extractHashtags(temp);		
		for (String i : list) {
			temp = StringUtils.delete(temp, "#"+i);
		}

		list = extractor.extractMentionedScreennames(temp);		
		for (String i : list) {
			temp = StringUtils.delete(temp, "@"+i);
		}
		
		list = extractor.extractCashtags(temp);		
		for (String i : list) {
			temp = StringUtils.delete(temp, i);
		}
	 	
		String s = extractor.extractReplyScreenname(temp);		
		temp = StringUtils.delete(temp, s);
		temp = StringUtils.delete(temp, "RT :");
		temp = StringUtils.delete(temp, "RT");
		temp = StringUtils.delete(temp, "&amp;");
		temp = StringUtils.trimLeadingWhitespace(temp);
		
        Pattern unicodeOutliers = Pattern.compile("[^\\x00-\\x7F]",
                Pattern.UNICODE_CASE | Pattern.CANON_EQ
                        | Pattern.CASE_INSENSITIVE);
        Matcher unicodeOutlierMatcher = unicodeOutliers.matcher(temp);

        temp =  unicodeOutlierMatcher.replaceAll(" ");
  
        if(temp.length() < 5)
        	return 2;
        
		// Analyze sentiment
		return NLP.findSentiment(temp);
	}
}
