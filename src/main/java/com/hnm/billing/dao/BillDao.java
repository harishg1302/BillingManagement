package com.hnm.billing.dao;

import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.model.Connection;
import com.hnm.billing.model.Supplier;
import com.hnm.billing.model.User;

import java.util.List;

public interface BillDao {

    ConnectionDTO generateBill(long userId);
    Bill saveBill(Bill bill);
    Connection getConnectionById(long connectionId);
    List<Supplier> getSuppliersByConnectionType(String connectionType);
    Connection saveConnection(Connection connection, long supplierId);
    List<Connection> getConnectionsByUserId(long userId);
    List<Bill> getBillsByUserIdAndConnectionType(long userId, String connectionType);
    String payBill(long billId, long lateFee);
    List<Bill> getAllBills();
    List<User> getAllUsers();
}
