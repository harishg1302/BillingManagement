package com.hnm.billing.dao.impl;

import com.hnm.billing.dao.WalletDao;
import com.hnm.billing.model.User;
import com.hnm.billing.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Override
    public Wallet getWalletWithUpdatedBalance(int userId, double addAmount) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Wallet wallet = mongoTemplate.findOne(query, Wallet.class);
        if(wallet != null) {
            double totalBalance = wallet.getBalance() + addAmount;
            Update updateBalance = new Update();
            updateBalance.set("balance", totalBalance);
            mongoTemplate.updateFirst(query, updateBalance, Wallet.class);
            return mongoTemplate.findOne(query, Wallet.class);
        } else {
            wallet = new Wallet();
            wallet.setUserId(userId);
            wallet.setBalance(addAmount);
            return mongoTemplate.save(wallet);
        }

    }
}
