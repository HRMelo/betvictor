package com.hrmelo.service.listenner;

import com.hrmelo.service.domain.Message;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import static org.springframework.messaging.simp.stomp.StompHeaderAccessor.wrap;

@Component
public class WebSocketEventListener {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    public WebSocketEventListener(SimpMessageSendingOperations simpMessageSendingOperations) {
        this.simpMessageSendingOperations = simpMessageSendingOperations;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent session) {
        final StompHeaderAccessor headerAccessor = wrap(session.getMessage());

        final String userName = (String) headerAccessor.getSessionAttributes().get("username");

        final Message message = new Message(userName, null);
        simpMessageSendingOperations.convertAndSend("topic/messages", message);
    }
}
