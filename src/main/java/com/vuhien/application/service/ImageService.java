package com.vuhien.application.service;

import com.vuhien.application.entity.Image;
import org.springframework.stereotype.Service;

@Service
public interface ImageService {
    void saveImage(Image image);
    void deleteImage(String uploadDir,String filename);
}
