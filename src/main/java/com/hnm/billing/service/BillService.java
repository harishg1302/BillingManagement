package com.hnm.billing.service;

import com.hnm.billing.dto.ConnectionDTO;

public interface BillService {
    ConnectionDTO generateBill(long userId);
}
