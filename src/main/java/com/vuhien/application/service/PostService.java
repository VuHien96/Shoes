package com.vuhien.application.service;

import com.vuhien.application.entity.Post;
import com.vuhien.application.entity.User;
import com.vuhien.application.model.dto.PageableDTO;
import com.vuhien.application.model.request.CreatePostRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    PageableDTO adminGetListPost(String title, String status, int page);
    Post createPost(CreatePostRequest createPostRequest, User user);
    void updatePost(CreatePostRequest createPostRequest,User user, Long id);
    void deletePost(long id);
    Post getPostById(long id);
    Page<Post> adminGetListPosts(String title, String status, Integer page);
}
