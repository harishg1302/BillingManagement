package com.hnm.billing.service.impl;

import com.hnm.billing.dao.BillDao;
import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.model.Connection;
import com.hnm.billing.model.Supplier;
import com.hnm.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Connection getConnectionById(long connectionId) {
        return billDao.getConnectionById(connectionId);
    }

    @Override
    public List<Supplier> getSuppliersByConnectionType(String connectionType) {
        return billDao.getSuppliersByConnectionType(connectionType);
    }

    @Override
    public Connection saveConnection(Connection connection, long supplierId) {
        return billDao.saveConnection(connection, supplierId);
    }

    @Override
    public List<Connection> getConnectionsByUserId(long userId) {
        return billDao.getConnectionsByUserId(userId);
    }

    @Override
    public List<Bill> getBillsByUserIdAndConnectionType(long userId, String connectionType) {
        return billDao.getBillsByUserIdAndConnectionType(userId, connectionType);
    }

    @Override
    public String payBill(long billId, long lateFee) {
        return billDao.payBill(billId, lateFee);
    }

    @Override
    public List<Bill> getAllBills() {
        return billDao.getAllBills();
    }

}
