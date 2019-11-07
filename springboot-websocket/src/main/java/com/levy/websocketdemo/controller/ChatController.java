package com.levy.websocketdemo.controller;

import com.levy.websocketdemo.model.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    //注入SimpMessagingTemplate 用于点对点消息发送
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        logger.info("send message:" + chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        logger.info("add user:" + chatMessage);
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/sendPrivateMessage") //这里是客户端发送消息对应的路径，等于configureMessageBroker中配置的setApplicationDestinationPrefixes + 这路径即 /app/sendPrivateMessage
    public void sendPrivateMessage(@Payload  ChatMessage msg, Principal principal) {
        msg.setSender(principal.getName());
        //将消息推送到指定路径上
        messagingTemplate.convertAndSendToUser(msg.getSender(), "topic/chat", msg);
    }

    /*
    注释方式推不过去这里没调通,有大神的话慢慢研究吧
    @SendToUser(value = "/topic/chat",broadcast=false)
    public ChatMessage sendPrivateMessage(@Payload  ChatMessage msg,Principal principal) {
        msg.setSender(principal.getName());
        return msg;
    }*/

}
