package com.sparta.cloneslack.service;

import com.sparta.cloneslack.dto.ChatRoomListDto;
import com.sparta.cloneslack.dto.ChatRoomRequestDto;
import com.sparta.cloneslack.dto.ChatRoomResponseDto;
import com.sparta.cloneslack.dto.InvitationDto;
import com.sparta.cloneslack.model.ChatRoom;
import com.sparta.cloneslack.model.User;
import com.sparta.cloneslack.repository.ChatRoomRepository;
import com.sparta.cloneslack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    //레디스 저장소 사용
    //key hashKey value 구조
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOpsEnterInfo;

    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    public static final String ENTER_INFO = "ENTER_INFO"; // 채팅룸에 입장한 클라이언트의 sessionId 와 채팅룸 id 를 맵핑한 정보 저장

    // 채팅방 생성
    public ChatRoomResponseDto createChatRoom(ChatRoomRequestDto requestDto) {
        ChatRoom chatRoom = new ChatRoom(requestDto, userService);
        chatRoomRepository.save(chatRoom);
        return new ChatRoomResponseDto(chatRoom, userService.findById(requestDto.getUserId()));
    }

    // 유저가 들어가있는 전체 채팅방 조회
    public List<ChatRoomListDto> getAllChatRooms(User user) {
        List<ChatRoomListDto> userChatRoom = new ArrayList<>();
        for (ChatRoom chatRoom : chatRoomRepository.findAllByOrderByCreatedAtDesc()) {
            System.out.println(chatRoom.getUserList());
            for(int i = 0; i < chatRoom.getUserList().size(); i++){
                if(chatRoom.getUserList().get(i).getId().equals(user.getId())) {
                    userChatRoom.add(new ChatRoomListDto(chatRoom, chatRoom.getUserList().get(i)));
                }
            }
        }
        return userChatRoom;
    }

    // 개별 채팅방 조회
    public ChatRoomResponseDto getEachChatRoom(Long id, User user) {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 채팅방이 존재하지 않습니다.")
        );
        return new ChatRoomResponseDto(chatRoom, user);
    }

    // 유저가 입장한 채팅방 ID 와 유저 세션 ID 맵핑 정보 저장
    //Enter라는 곳에 sessionId와 roomId를 맵핑시켜놓음
    public void setUserEnterInfo(String sessionId, String roomId) {
        hashOpsEnterInfo.put(ENTER_INFO, sessionId, roomId);
    }

    // 유저 세션으로 입장해 있는 채팅방 ID 조회
    public String getUserEnterRoomId(String sessionId) {
        return hashOpsEnterInfo.get(ENTER_INFO, sessionId);
    }

    // 유저 세션정보와 맵핑된 채팅방 ID 삭제
    //한 유저는 하나의 룸 아이디에만 맵핑되어있다!
    // 실시간으로 보는 방은 하나이기 떄문이다.
    public void removeUserEnterInfo(String sessionId) {
        hashOpsEnterInfo.delete(ENTER_INFO, sessionId);
    }

    @Transactional
    public ResponseEntity<?> inviteUser(InvitationDto invitationDto) {
        Long roomId = invitationDto.getRoomId();
        String username = invitationDto.getUsername();
        Optional<User> tmp = userRepository.findByUsername(username);
        if(!tmp.isPresent()){
            return ResponseEntity.badRequest().body(new IllegalArgumentException("유저 정보를 확인해주세요"));
        }
        User user = tmp.get();
        ChatRoom findRoom = chatRoomRepository.findById(roomId).orElseThrow(
                () -> new IllegalArgumentException("방번호를 확인해주세요")
        );
        findRoom.getUserList().add(user);
        chatRoomRepository.save(findRoom);
        return ResponseEntity.ok().body("good");

    }

    @Transactional
    public ResponseEntity<?> outChatRoom(Long roomId, User user) {
        Optional<ChatRoom> tmp = chatRoomRepository.findById(roomId);
        if(!tmp.isPresent()){
            return ResponseEntity.badRequest().body(new IllegalArgumentException("그런 방은 존재하지 않습니다."));
        }
        ChatRoom chatRoom = tmp.get();

        for (User usr : chatRoom.getUserList()) {
            if(usr.getId().equals(user.getId())){
                chatRoom.getUserList().remove(usr);
                break;
            }
        }
        chatRoomRepository.save(chatRoom);
        return ResponseEntity.ok().body("채팅방 나가기 성공!");
    }
}
