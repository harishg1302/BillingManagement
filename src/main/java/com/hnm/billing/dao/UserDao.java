package com.hnm.billing.dao;

import com.hnm.billing.dto.LoginDTO;
import com.hnm.billing.model.User;

import java.util.List;

public interface UserDao {
    User saveUser(User user);
    User loginUser(LoginDTO loginDTO);
    User getByEmailId(String emailId);
    List<User> getAllUsers();
}
