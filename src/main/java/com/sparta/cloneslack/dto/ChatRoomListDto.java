package com.sparta.cloneslack.dto;

import com.sparta.cloneslack.model.ChatRoom;
import com.sparta.cloneslack.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ChatRoomListDto {

    private Long id;
    private String chatRoomName;
    private List<User> userList = new ArrayList<>();
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ChatRoomListDto(ChatRoom chatRoom, User user) {
        this.id = chatRoom.getId();
        this.chatRoomName = chatRoom.getChatRoomName();
        this.userList = chatRoom.getUserList();
        this.username = user.getUsername();
        this.createdAt = chatRoom.getCreatedAt();
        this.modifiedAt = chatRoom.getModifiedAt();
    }
}