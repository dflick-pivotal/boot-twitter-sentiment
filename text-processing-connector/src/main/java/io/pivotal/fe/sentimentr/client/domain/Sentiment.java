package io.pivotal.fe.sentimentr.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Sentiment {

    private String label;
    private Probability probability;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Probability getProbability() {
		return probability;
	}
	public void setProbability(Probability probability) {
		this.probability = probability;
	}
	@Override
	public String toString() {
		return "Sentiment [label=" + label + ", probability=" + probability + "]";
	}
    
}
