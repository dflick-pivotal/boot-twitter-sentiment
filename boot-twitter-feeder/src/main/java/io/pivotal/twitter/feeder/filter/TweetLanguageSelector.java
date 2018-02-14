package io.pivotal.twitter.feeder.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.core.MessageSelector;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.messaging.Message;

public class TweetLanguageSelector implements MessageSelector {

	@Value("${twitter_language:de}")
	String language;
	
	@Override
	public boolean accept(Message<?> message) {
		if(((Tweet)message.getPayload()).getLanguageCode().equals(language))
				return true;
		return false;
	}

}
 