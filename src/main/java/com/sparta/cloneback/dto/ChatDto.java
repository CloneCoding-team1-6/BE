package com.sparta.cloneback.dto;

import com.sparta.cloneback.model.ChatMessage;
import lombok.Data;

@Data
public class ChatDto {

    private ChatMessage.MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸사람
    private String message; // 메시지

}