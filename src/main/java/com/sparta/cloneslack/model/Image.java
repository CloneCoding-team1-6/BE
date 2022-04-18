package com.sparta.cloneslack.model;

import com.sparta.cloneslack.dto.ImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fileUrl;

    @Column
    private String fileName;

    public Image(ImageDto imageDto) {
        this.fileUrl = imageDto.getFileUrl();
        this.fileName = imageDto.getFileName();
    }
}
