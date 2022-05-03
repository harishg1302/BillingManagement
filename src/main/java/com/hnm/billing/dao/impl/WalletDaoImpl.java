package com.hnm.billing.dao.impl;

import com.hnm.billing.dao.WalletDao;
import com.hnm.billing.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WalletDaoImpl implements WalletDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Wallet getWalletByUserId(int userId) {
        Wallet wallet = mongoTemplate.findById(userId, Wallet.class);
        return wallet;
    }
}
