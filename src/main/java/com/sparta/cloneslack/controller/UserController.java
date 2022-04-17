package com.sparta.cloneslack.controller;

import com.sparta.cloneslack.dto.NicknameCheckDto;
import com.sparta.cloneslack.dto.SignupRequestDto;
import com.sparta.cloneslack.dto.UserInfoDto;
import com.sparta.cloneslack.dto.UsernameCheckDto;
import com.sparta.cloneslack.model.User;
import com.sparta.cloneslack.repository.UserRepository;
import com.sparta.cloneslack.service.UserService;
import com.sparta.cloneslack.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    // 회원 가입 요청 처리
    @PostMapping("/api/register")
    public String registerUser(@RequestBody SignupRequestDto requestDto) {
        return userService.registerUser(requestDto);
    }

    //아이디 중복 체크
    @PostMapping("/api/idCheck")
    public String usernameCheck(@RequestBody UsernameCheckDto usernameCheckDto){
        return userService.usernameCheck(usernameCheckDto);
    }

    //닉네임 중복 체크
    @PostMapping("/api/nickName")
    public String nicknameCheck(@RequestBody NicknameCheckDto nicknameCheckDto){
        return userService.nicknameCheck(nicknameCheckDto);
    }

    //로그인 여부 확인
    @GetMapping("/api/idLogin")
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails.getUser().getId());
        return userService.getUserInfo(userDetails);
    }

    //전체 유저 조회
    @GetMapping("/api/users")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    //카카오 로그인 요청 처리하기
//    @GetMapping("/user/kakao/callback")
//    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
//        kakaoUserService.kakaoLogin(code);
//        return "redirect:/";
//    }
}