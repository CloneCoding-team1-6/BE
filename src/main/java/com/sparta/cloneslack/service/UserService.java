package com.sparta.cloneslack.service;


import com.sparta.cloneslack.dto.NicknameCheckDto;
import com.sparta.cloneslack.dto.SignupRequestDto;
import com.sparta.cloneslack.dto.UserInfoDto;
import com.sparta.cloneslack.dto.UsernameCheckDto;
import com.sparta.cloneslack.model.User;
import com.sparta.cloneslack.repository.UserRepository;
import com.sparta.cloneslack.security.UserDetailsImpl;
import com.sparta.cloneslack.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;


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
        userRepository.save(new User(requestDto));
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

        return new UserInfoDto(userId, username, nickname);

    }

    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("찾는 유저가 없습니다")
        );
        return user;
    }
}

