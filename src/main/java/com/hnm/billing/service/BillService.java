package com.hnm.billing.service;

import com.hnm.billing.dto.BillDTO;
import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.model.Connection;
import com.hnm.billing.model.Supplier;

import java.util.List;

public interface BillService {
    ConnectionDTO generateBill(long userId);
    Bill saveBill(Bill bill);
    Connection getConnectionById(long connectionId);
    List<Supplier> getSuppliersByConnectionType(String connectionType);
    Connection saveConnection(Connection connection, long supplierId);
    List<Connection> getConnectionsByUserId(long userId);
    List<Bill> getBillsByUserIdAndConnectionType(long userId, String connectionType);
    String payBill(long billId, long lateFee);
    List<BillDTO> getAllBills(String connectionType, String billStatus);
}
