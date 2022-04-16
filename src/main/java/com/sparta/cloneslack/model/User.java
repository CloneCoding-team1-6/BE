package com.sparta.cloneslack.model;

import com.sparta.cloneslack.dto.SignupRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column
    private String imgUrl;

    @Column(unique = true)
    private Long kakaoId;

    public User(SignupRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.nickname = requestDto.getNickName();
        this.password = requestDto.getPassword();
    }

    public User(String username, String nickname, String password, Long kakaoId) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.kakaoId = kakaoId;
    }
}
