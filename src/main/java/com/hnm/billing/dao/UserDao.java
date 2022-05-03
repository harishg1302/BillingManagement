package com.hnm.billing.dao;

import com.hnm.billing.dto.LoginDTO;
import com.hnm.billing.model.User;

public interface UserDao {
    User saveUser(User user);
    User loginUser(LoginDTO loginDTO);
}
