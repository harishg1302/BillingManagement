package com.hnm.billing.dao;

import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;

public interface BillDao {

    ConnectionDTO generateBill(long userId);
    Bill saveBill(Bill bill);
}
