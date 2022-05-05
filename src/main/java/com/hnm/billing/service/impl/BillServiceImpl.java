package com.hnm.billing.service.impl;

import com.hnm.billing.dao.BillDao;
import com.hnm.billing.dto.BillDTO;
import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.model.Connection;
import com.hnm.billing.model.Supplier;
import com.hnm.billing.model.User;
import com.hnm.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<BillDTO> getAllBills(String connectionType, String billStatus) {
        List<User> users = billDao.getAllUsers();
        List<Bill> bills = billDao.getAllBills(connectionType, billStatus);
        List<BillDTO> billDTOS = new ArrayList<>(bills.size());
        if(!CollectionUtils.isEmpty(users) && !CollectionUtils.isEmpty(bills)) {
            bills.forEach(bill -> {
                BillDTO billDTO = getBillDTO(bill);
                Optional<User> selectedUser = users.stream().filter(user -> user.getId() == bill.getUserId()).findFirst();
                if (selectedUser.isPresent()) {
                    billDTO.setEmail(selectedUser.get().getEmail());
                }
                billDTOS.add(billDTO);
            });
        }
        return billDTOS;
    }

    private BillDTO getBillDTO(Bill bill) {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(bill.getId());
        billDTO.setUserId(bill.getUserId());
        billDTO.setConnection(bill.getConnection());
        billDTO.setBillingDate(bill.getBillingDate());
        billDTO.setBillStatus(bill.getBillStatus());
        billDTO.setDueDate(bill.getDueDate());
        billDTO.setAmount(bill.getAmount());
        billDTO.setLateFee(bill.getLateFee());
        billDTO.setTotalAmount(bill.getTotalAmount());
        return billDTO;
    }

}
