package com.sparta.cloneback.model;

import com.sparta.cloneback.dto.ChatRoomRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ChatRoom extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false)
    private String roomName;

    public ChatRoom(ChatRoomRequestDto chatRoomRequestDto) {
        this.roomName = chatRoomRequestDto.getRoomName();

    }
}


