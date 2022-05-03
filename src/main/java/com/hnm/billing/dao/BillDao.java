package com.hnm.billing.dao;

import com.hnm.billing.dto.ConnectionDTO;

public interface BillDao {

    ConnectionDTO generateBill(long userId);
}
