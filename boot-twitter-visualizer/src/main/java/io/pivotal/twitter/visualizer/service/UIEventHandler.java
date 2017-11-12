package io.pivotal.twitter.visualizer.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import io.pivotal.twitter.model.TweetData;
import io.pivotal.twitter.visualizer.service.SentimentService;


@Component
public class UIEventHandler extends TextWebSocketHandler{

	@Autowired
	private SentimentService sentimentService;

	@Autowired
	private Jackson2JsonObjectMapper objMap;
    private Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }
 
    public void onTweetDataReceived(TweetData td) {
    	System.out.println(">>> onStatsReceived was invoked with " + td.toString());
    	sentimentService.doStats();
    	for (WebSocketSession session : sessions.values()) {
            if (session != null && session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(objMap.toJson(td)));
                    System.out.println(">>> Message" + td.toString() + " was sent" + "websocket session: " + session);
                } catch (IOException e) {
                    System.out.println("IOException caught when sending message: " + e);
                } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }
}
