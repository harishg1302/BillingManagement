package com.hnm.billing.service.impl;

import com.hnm.billing.dao.UserDao;
import com.hnm.billing.dto.LoginDTO;
import com.hnm.billing.model.User;
import com.hnm.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User loginUser(LoginDTO loginDTO) {
        return userDao.loginUser(loginDTO);
    }

    @Override
    public User getByEmailId(String emailId) {
        return userDao.getByEmailId(emailId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
