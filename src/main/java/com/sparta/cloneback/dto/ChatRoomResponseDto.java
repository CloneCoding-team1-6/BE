package com.sparta.cloneback.dto;

import com.sparta.cloneback.model.ChatRoom;
import com.sparta.cloneback.model.User;

public class ChatRoomResponseDto {

    private Long id;
    private String chatRoomName;
    private User user;

    public ChatRoomResponseDto(ChatRoom chatRoom, User user) {
        this.id = chatRoom.getId();
        this.chatRoomName = chatRoom.getChatRoomName();
        this.user = user;
    }
}
