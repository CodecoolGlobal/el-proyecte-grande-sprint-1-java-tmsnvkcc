package com.codecool.service.user;

import com.codecool.dao.UserDAO;
import com.codecool.dto.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void createUserAccount(NewUserDTO user){
        // TODO: hashing password and generating uuid
        userDAO.addUser(user.email(), user.username(), false);
    }
}
