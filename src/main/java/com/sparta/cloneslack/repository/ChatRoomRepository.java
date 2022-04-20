package com.sparta.cloneslack.repository;

import com.sparta.cloneslack.model.ChatRoom;
import com.sparta.cloneslack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findAllByOrderByCreatedAtDesc();
}
