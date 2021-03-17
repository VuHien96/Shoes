package com.vuhien.application.repository;

import com.vuhien.application.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,String> {
    Image findByLink(String link);
}
