package com.hnm.billing.service;

import com.hnm.billing.dto.LoginDTO;
import com.hnm.billing.model.Supplier;
import com.hnm.billing.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User loginUser(LoginDTO loginDTO);
    User getByEmailId(String emailId);
    List<User> getAllUsers();
}
