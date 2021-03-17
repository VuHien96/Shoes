package com.vuhien.application.service.impl;

import com.vuhien.application.entity.Image;
import com.vuhien.application.exception.BadRequestException;
import com.vuhien.application.exception.InternalServerException;
import com.vuhien.application.repository.ImageRepository;
import com.vuhien.application.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Component
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    @Override
    @Transactional(rollbackFor = InternalServerException.class)
    public void deleteImage(String uploadDir, String filename) {

        //Lấy đường dẫn file
        String link = "/media/static/" + filename;
        //Kiểm tra link
        Image image = imageRepository.findByLink(link);
        if (image == null) {
            throw new BadRequestException("File không tồn tại");
        }

        //Xóa file trong databases
        imageRepository.delete(image);

        //Kiểm tra file có tồn tại trong thư mục
        File file = new File(uploadDir + "/" + filename);
        if (file.exists()) {
            //Xóa file ở thư mục
            if (!file.delete()) {
                throw new InternalServerException("Lỗi khi xóa ảnh!");
            }
        }
    }
}
