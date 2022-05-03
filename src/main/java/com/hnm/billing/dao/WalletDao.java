package com.hnm.billing.dao;

import com.hnm.billing.model.Wallet;

public interface WalletDao {

    Wallet getWalletByUserId(long userId);
    Wallet getWalletWithUpdatedBalance(long userId, double addAmount);
}
