package com.hnm.billing.dao;

import com.hnm.billing.model.Wallet;

public interface WalletDao {

    Wallet getWalletByUserId(int userId);
}