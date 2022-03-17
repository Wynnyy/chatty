package sk.wynny.chatty.controler;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import sk.wynny.chatty.model.DataChat;
@Controller
public class PmCc {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public DataChat sendMessage(@Payload DataChat chatMessagePojo) {
        return chatMessagePojo;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public DataChat addUser(@Payload DataChat chatMessagePojo, SimpMessageHeaderAccessor headerAccessor) {

// Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessagePojo.getSender());
        return chatMessagePojo;
    }
}