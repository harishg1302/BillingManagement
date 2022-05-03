package com.hnm.billing.service.impl;

import com.hnm.billing.dao.WalletDao;
import com.hnm.billing.model.Wallet;
import com.hnm.billing.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletDao walletDao;

    @Override
    public Wallet getWalletByUserId(int userId) {
        return walletDao.getWalletByUserId(userId);
    }

    @Override
    public Wallet getWalletWithUpdatedBalance(int userId, double addAmount) {
        return walletDao.getWalletWithUpdatedBalance(userId, addAmount);
    }
}
