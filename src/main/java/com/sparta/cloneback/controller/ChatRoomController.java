package com.sparta.cloneback.controller;

import com.sparta.cloneback.dto.ChatRoomRequestDto;
import com.sparta.cloneback.model.ChatRoom;
import com.sparta.cloneback.repository.ChatRoomRepository;
import com.sparta.cloneback.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatRoomRepository chatRoomRepository;

    // 채팅방 생성
    @PostMapping("/rooms")
    public void createRoom(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        chatRoomService.createChatRoom(chatRoomRequestDto);
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> room() {
        return chatRoomRepository.findAll();


    }
}
