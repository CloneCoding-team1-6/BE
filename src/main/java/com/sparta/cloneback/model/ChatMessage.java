package com.sparta.cloneback.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ChatMessage extends Timestamped{

    public enum MessageType {
        ENTER, TALK
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private MessageType type; // 메시지 타입

    @Column
    private Long roomId; // 방번호

    @Column
    private Long userId;

    @Column
    private String sender; // 메시지 보낸사람

    @Column
    private String message; // 메시지

    @Column
    private String createdAt;

    public ChatMessage(MessageType type, Long roomId, Long userId, String sender, String message) {
        this.type = type;
        this.roomId = roomId;
        this.user = null;
        this.userId = userId;
        this.sender = sender;
        this.message = message;
        this.createdAt = createdAt;
    }

}
