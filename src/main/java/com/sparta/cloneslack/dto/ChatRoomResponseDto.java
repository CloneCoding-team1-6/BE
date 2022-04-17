package com.sparta.cloneslack.dto;

import com.sparta.cloneslack.model.ChatRoom;
import com.sparta.cloneslack.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
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
