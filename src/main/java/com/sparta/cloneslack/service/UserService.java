package com.sparta.cloneslack.service;


import com.sparta.cloneslack.dto.NicknameCheckDto;
import com.sparta.cloneslack.dto.SignupRequestDto;
import com.sparta.cloneslack.dto.UserInfoDto;
import com.sparta.cloneslack.dto.UsernameCheckDto;
import com.sparta.cloneslack.model.ChatRoom;
import com.sparta.cloneslack.model.User;
import com.sparta.cloneslack.repository.ChatRoomRepository;
import com.sparta.cloneslack.repository.UserRepository;
import com.sparta.cloneslack.security.UserDetailsImpl;
import com.sparta.cloneslack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;
    private final ChatRoomRepository chatRoomRepository;


    //회원가입 확인
    public String registerUser(SignupRequestDto requestDto) {
        String msg = "회원가입이 완료되었습니다.";

        try {
            //회원가입 확인
            validator.signupValidate(requestDto);
        } catch (IllegalArgumentException e) {
            msg = e.getMessage();
            return msg;
        }

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User user = new User(requestDto);
        userRepository.save(user);

        //전체 채팅방에 모든 유저 회원가입하자마자 등록
//         user2 = new ArrayList<>();
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(1L);

        ChatRoom chatRoom1 = chatRoom.get();

        List<User> user2 = chatRoom1.getUserList();
        user2.add(user);
        chatRoom1.setUserList(user2);
        chatRoomRepository.save(chatRoom1);

        return msg;
    }

    //아이디 중복검사
    public String usernameCheck(UsernameCheckDto usernameCheckDto) {
        String msg = "사용가능한 아이디 입니다.";

        try {
            validator.idCheck(usernameCheckDto);
        } catch (IllegalArgumentException e) {
            msg = e.getMessage();
            return msg;
        }
        return msg;
    }

    //닉네임 중복검사
    public String nicknameCheck(NicknameCheckDto nicknameCheckDto) {
        String msg = "사용가능한 닉네임 입니다.";

        try {
            validator.nickCheck(nicknameCheckDto);
        } catch (IllegalArgumentException e) {
            msg = e.getMessage();
            return msg;
        }
        return msg;
    }


    //로그인한 유저 정보 가져오기
    public UserInfoDto getUserInfo(UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        String nickname = userDetails.getUser().getNickname();
        Long userId = userDetails.getUser().getId();
        String imgUrl = userDetails.getUser().getImgUrl();

        return new UserInfoDto(userId, username, nickname, imgUrl);

    }

    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("찾는 유저가 없습니다")
        );
        return user;
    }
}

