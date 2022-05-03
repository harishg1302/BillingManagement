package com.hnm.billing.service;

import com.hnm.billing.dto.LoginDTO;
import com.hnm.billing.model.User;

public interface UserService {

    User saveUser(User user);
    User loginUser(LoginDTO loginDTO);
    User getByEmailId(String emailId);
}
