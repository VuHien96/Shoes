package com.vuhien.application.service;


import com.vuhien.application.entity.User;
import com.vuhien.application.model.dto.UserDTO;
import com.vuhien.application.model.request.CreateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> getListUsers();
    User createUser(CreateUserRequest createUserRequest);
}
