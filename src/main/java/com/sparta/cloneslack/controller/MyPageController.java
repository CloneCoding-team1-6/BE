package com.sparta.cloneslack.controller;

import com.sparta.cloneslack.dto.ImageRequestDto;
import com.sparta.cloneslack.security.UserDetailsImpl;
import com.sparta.cloneslack.service.ImageService;
import com.sparta.cloneslack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MyPageController {
    private final ImageService imageService;

    @Autowired
    public MyPageController(ImageService imageService) {
        this.imageService = imageService;
    }

    //프로필 이미지 업로드
    @PutMapping("/api/userImage")
    public String upload(
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws IOException {
        ImageRequestDto imageRequestDto = new ImageRequestDto(userDetails.getUser().getId(), file);
        return imageService.upload(imageRequestDto, "static");
    }
}
