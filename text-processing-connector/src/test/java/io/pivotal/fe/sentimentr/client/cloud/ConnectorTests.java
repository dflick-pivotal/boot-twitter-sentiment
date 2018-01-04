package io.pivotal.fe.sentimentr.client.cloud;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.pivotal.fe.sentimentr.client.domain.Sentiment;
import io.pivotal.fe.sentimentr.client.facade.SentimentrFacade;

public class ConnectorTests {

	private static final Logger log = LoggerFactory.getLogger(ConnectorTests.class);

	@Test
	public void test() {
	    SentimentrFacade sentimentrFacade = new SentimentrFacade("http://text-processing.com/api/sentiment/");
	    Sentiment sentiment = sentimentrFacade.getSentiment("good");
	    log.info(sentiment.toString());
	    sentiment = sentimentrFacade.getSentiment("bad");
	    log.info(sentiment.toString());
	}

}
