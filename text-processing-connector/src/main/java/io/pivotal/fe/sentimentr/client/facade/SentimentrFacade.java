package io.pivotal.fe.sentimentr.client.facade;

import org.springframework.web.client.RestTemplate;

import io.pivotal.fe.sentimentr.client.domain.Sentiment;

public class SentimentrFacade {
	
	private String sentimentrUri;

	public SentimentrFacade()
	{
	}
	public SentimentrFacade(String sentimentrUri)
	{
		this.sentimentrUri = sentimentrUri;
	}
	
	public Sentiment getSentiment(String text)
	{
		RestTemplate restTemplate = new RestTemplate();
		Sentiment sentiment;
		try
		{
			sentiment = restTemplate.postForObject(sentimentrUri, "text="+text, Sentiment.class);
		}
		catch (Exception e)
		{
			sentiment = new Sentiment();
			sentiment.setLabel("neutral");
		}
		return sentiment;
	}
	
	public int getSentimentAsNumber(String text)
	{
	    int value = 2;
		Sentiment sentiment = getSentiment(text);
		
		if (sentiment.getLabel().equals("neg"))
			value = 1;
		if (sentiment.getLabel().equals("pos"))
			value = 3;
		
		return value;
	}

}
