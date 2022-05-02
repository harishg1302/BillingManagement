package com.hnm.billing.dao.impl;

import com.hnm.billing.dao.UserDao;
import com.hnm.billing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User saveUser(User user) {
        return mongoTemplate.save(user);
    }
}
