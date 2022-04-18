package com.sparta.cloneslack.repository;

import com.sparta.cloneslack.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
