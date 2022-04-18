package com.sparta.cloneslack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {

    private Long userId;
    private String username;
    private String nickname;
    private String imgUrl;

    public UserInfoDto(Long userId, String username, String nickname, String imgUrl) {
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.imgUrl = imgUrl;
    }
}
