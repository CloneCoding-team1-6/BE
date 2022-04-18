package com.sparta.cloneslack.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {
    //    private Long id;
    private Long userId;
    private String fileUrl;
    private String fileName;

    public ImageDto(String fileUrl, String fileName) {
        this.fileUrl = fileUrl;
        this.fileName = fileName;
    }
}
