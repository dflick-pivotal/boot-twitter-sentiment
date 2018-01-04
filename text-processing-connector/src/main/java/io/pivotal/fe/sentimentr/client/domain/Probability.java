package io.pivotal.fe.sentimentr.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Probability {
	private String neg;
	private String neutral;
	private String pos;
	public String getNeg() {
		return neg;
	}
	public void setNeg(String neg) {
		this.neg = neg;
	}
	public String getNeutral() {
		return neutral;
	}
	public void setNeutral(String neutral) {
		this.neutral = neutral;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	@Override
	public String toString() {
		return "Probability [neg=" + neg + ", neutral=" + neutral + ", pos=" + pos + "]";
	}

}
