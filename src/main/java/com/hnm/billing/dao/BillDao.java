package com.hnm.billing.dao;

import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.model.Connection;
import com.hnm.billing.model.Supplier;

import java.util.List;

public interface BillDao {

    ConnectionDTO generateBill(long userId);
    Bill saveBill(Bill bill);
    Connection getConnectionById(long connectionId);
    List<Supplier> getSuppliersByConnectionType(String connectionType);
}
