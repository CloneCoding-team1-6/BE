package com.sparta.cloneback.controller;

import com.sparta.cloneback.dto.ChatDto;
import com.sparta.cloneback.model.ChatMessage;
import com.sparta.cloneback.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;


    @MessageMapping("/api/message")
    public void message(@RequestBody ChatDto chatDto,@Header("token") String token) {

        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);

    }
}