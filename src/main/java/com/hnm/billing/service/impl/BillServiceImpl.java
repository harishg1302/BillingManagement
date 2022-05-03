package com.hnm.billing.service.impl;

import com.hnm.billing.dao.BillDao;
import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;

    @Override
    public ConnectionDTO generateBill(long userId) {
        return billDao.generateBill(userId);
    }

    @Override
    public Bill saveBill(Bill bill) {
        return billDao.saveBill(bill);
    }
}
