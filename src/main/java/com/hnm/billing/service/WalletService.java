package com.hnm.billing.service;

import com.hnm.billing.model.Wallet;

public interface WalletService {

    Wallet getWalletByUserId(int userId);
}
