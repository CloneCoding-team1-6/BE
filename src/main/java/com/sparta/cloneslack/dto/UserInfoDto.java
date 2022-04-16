package com.sparta.cloneslack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {

    private Long userId;
    private String username;
    private String nickname;

    public UserInfoDto(Long userId, String username, String nickname) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
    }
}
