package com.sparta.cloneslack.model;

import com.sparta.cloneslack.dto.ChatRoomRequestDto;
import com.sparta.cloneslack.service.UserService;
import com.sparta.cloneslack.timestamped.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class ChatRoom extends Timestamped {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @Column
    private String chatRoomName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<User> userList = new ArrayList<>();


    public ChatRoom(ChatRoomRequestDto requestDto, UserService userService) {
        this.chatRoomName = requestDto.getChatRoomName();
        this.userList.add(userService.findById(requestDto.getUserId()));
    }
}