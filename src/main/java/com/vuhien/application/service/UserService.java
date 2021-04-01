package com.vuhien.application.service;


import com.vuhien.application.entity.User;
import com.vuhien.application.model.dto.UserDTO;
import com.vuhien.application.model.request.ChangePasswordRequest;
import com.vuhien.application.model.request.CreateUserRequest;
import com.vuhien.application.model.request.UpdateProfileRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> getListUsers();

    User createUser(CreateUserRequest createUserRequest);

    void changePassword(User user, ChangePasswordRequest changePasswordRequest);

    User updateProfile(User user, UpdateProfileRequest updateProfileRequest);
}
