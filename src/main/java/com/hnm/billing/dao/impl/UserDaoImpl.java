package com.hnm.billing.dao.impl;

import com.hnm.billing.dao.UserDao;
import com.hnm.billing.dto.LoginDTO;
import com.hnm.billing.model.User;
import com.hnm.billing.model.Wallet;
import com.hnm.billing.service.impl.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public User saveUser(User user) {
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        return mongoTemplate.save(user);
    }

    @Override
    public User loginUser(LoginDTO loginDTO) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(loginDTO.getEmail()));
        query.addCriteria(Criteria.where("status").is(true));
        query.addCriteria(Criteria.where("password").is(loginDTO.getPassword()));
        User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    @Override
    public User getByEmailId(String emailId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("emailId").is(emailId));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public List<User> getAllUsers() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(true));
        List<User> user = mongoTemplate.find(query, User.class);
        return user;
    }
}
