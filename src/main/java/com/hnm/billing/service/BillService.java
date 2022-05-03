package com.hnm.billing.service;

import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.model.Connection;

public interface BillService {
    ConnectionDTO generateBill(long userId);
    Bill saveBill(Bill bill);
    Connection getConnectionById(long connectionId);
}
