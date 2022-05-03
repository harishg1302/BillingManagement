package com.hnm.billing.dao.impl;

import com.hnm.billing.dao.UserDao;
import com.hnm.billing.dto.LoginDTO;
import com.hnm.billing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User saveUser(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public User loginUser(LoginDTO loginDTO) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(loginDTO.getEmail()));
        query.addCriteria(Criteria.where("password").is(loginDTO.getPassword()));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }
}
