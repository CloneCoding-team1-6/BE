package com.sparta.cloneback.service;

import com.sparta.cloneback.dto.ChatRoomRequestDto;
import com.sparta.cloneback.model.ChatRoom;
import com.sparta.cloneback.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

        private final ChatRoomRepository chatRoomRepository;

    //채팅방 만들기.
    public void createChatRoom (ChatRoomRequestDto chatRoomRequestDto){

        ChatRoom chatRoom = new ChatRoom(chatRoomRequestDto);
        chatRoomRepository.save(chatRoom);
    }

 }
