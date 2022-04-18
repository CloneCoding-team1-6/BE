package com.sparta.cloneslack.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageRequestDto {
    private Long userId;
    private MultipartFile file;

    public ImageRequestDto(Long userId, MultipartFile file) {
        this.userId = userId;
        this.file = file;
    }
}
