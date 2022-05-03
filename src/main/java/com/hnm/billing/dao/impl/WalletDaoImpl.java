package com.hnm.billing.dao.impl;

import com.hnm.billing.dao.WalletDao;
import com.hnm.billing.model.Wallet;
import com.hnm.billing.service.impl.SequenceGeneratorService;
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

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Wallet getWalletByUserId(long userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Wallet.class);
    }

    @Override
    public Wallet getWalletWithUpdatedBalance(long userId, double addAmount) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Wallet wallet = mongoTemplate.findOne(query, Wallet.class);
        if(wallet != null) {
            Update updateBalance = new Update();
            updateBalance.set("balance", addAmount);
            mongoTemplate.updateFirst(query, updateBalance, Wallet.class);
            return mongoTemplate.findOne(query, Wallet.class);
        } else {
            wallet = new Wallet();
            wallet.setWalletId(sequenceGeneratorService.generateSequence(Wallet.SEQUENCE_NAME));
            wallet.setUserId(userId);
            wallet.setBalance(addAmount);
            return mongoTemplate.save(wallet);
        }

    }
}
