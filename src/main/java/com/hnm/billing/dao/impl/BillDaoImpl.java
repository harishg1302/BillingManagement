package com.hnm.billing.dao.impl;

import com.hnm.billing.dao.BillDao;
import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.model.BillStatus;
import com.hnm.billing.model.Connection;
import com.hnm.billing.model.ConnectionType;
import com.hnm.billing.model.Supplier;
import com.hnm.billing.model.User;
import com.hnm.billing.model.Wallet;
import com.hnm.billing.service.impl.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BillDaoImpl implements BillDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public ConnectionDTO generateBill(long userId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        query.addCriteria(Criteria.where("status").is(true));
        User user = mongoTemplate.findOne(query, User.class);
        if (user != null) {
            Query query1 = new Query();
            query1.addCriteria(Criteria.where("userId").is(userId));
            List<Connection> connectionList = mongoTemplate.find(query1, Connection.class);
            ConnectionDTO connectionDTO = new ConnectionDTO();
            connectionDTO.setConnectionList(connectionList);
            connectionDTO.setEmailId(user.getEmail());
            return connectionDTO;
        } else {
            return null;
        }
    }

    @Override
    public Bill saveBill(Bill bill) {

        bill.setId(sequenceGeneratorService.generateSequence(Bill.SEQUENCE_NAME));
        return mongoTemplate.save(bill);
    }

    @Override
    public Connection getConnectionById(long connectionId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(connectionId));
        return mongoTemplate.findOne(query, Connection.class);
    }

    @Override
    public List<Supplier> getSuppliersByConnectionType(String connectionType) {

        Query query = new Query();
        query.addCriteria(Criteria.where("connectionType").is(ConnectionType.valueOf(connectionType).getDisplayName()));
        return mongoTemplate.find(query, Supplier.class);
    }

    @Override
    public Connection saveConnection(Connection connection, long supplierId) {

        if (connection.getConnectionType().equalsIgnoreCase(ConnectionType.MOBILE.getDisplayName())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("connectionNumber").is(connection.getConnectionNumber()));
            Connection existingConnection = mongoTemplate.findOne(query, Connection.class);
            if (existingConnection != null) {
                throw new RuntimeException("DUPLICATE_CONNECTION");
            }
        } else {
            long connectionSequence = sequenceGeneratorService.generateConnectionNumber("connection_number_generator");
            String connectionNumber = connection.getConnectionType() + connectionSequence;
            connection.setConnectionNumber(connectionNumber);
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(supplierId));
        Supplier supplier = mongoTemplate.findOne(query, Supplier.class);
        connection.setSupplier(supplier);
        connection.setId(sequenceGeneratorService.generateSequence(Connection.SEQUENCE_NAME));
        return mongoTemplate.save(connection);
    }

    @Override
    public List<Connection> getConnectionsByUserId(long userId) {

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Connection.class);
    }

    @Override
    public List<Bill> getBillsByUserIdAndConnectionType(long userId, String connectionType) {

        String connectionTypeValue = ConnectionType.valueOf(connectionType).getDisplayName();
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        List<Bill> bills = mongoTemplate.find(query, Bill.class);
        if (!CollectionUtils.isEmpty(bills)) {
            return bills.stream().filter(bill -> bill.getConnection().getConnectionType().equalsIgnoreCase(connectionTypeValue)).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Override
    public String payBill(long billId, Double lateFee) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(billId));
        Bill bill = mongoTemplate.findOne(query, Bill.class);
        bill.setLateFee(lateFee);
        bill.setTotalAmount(bill.getAmount() + lateFee);
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("userId").is(bill.getUserId()));
        Wallet wallet = mongoTemplate.findOne(query1, Wallet.class);
        if (wallet != null) {
            if (wallet.getBalance() >= bill.getTotalAmount()) {

                Update updateBillStatus = new Update();
                updateBillStatus.set("billStatus", BillStatus.PAID);
                updateBillStatus.set("lateFee", bill.getLateFee());
                updateBillStatus.set("totalAmount", bill.getTotalAmount());
                mongoTemplate.updateFirst(query, updateBillStatus, Bill.class);

                Update updateWalletBalance = new Update();
                double updatedBalance = wallet.getBalance() - bill.getTotalAmount();
                updateWalletBalance.set("balance", updatedBalance);
                mongoTemplate.updateFirst(query1, updateWalletBalance, Wallet.class);

                return "PAID";
            } else {
                return "AMOUNT_INSUFFICIENT";
            }
        } else {
            return "AMOUNT_INSUFFICIENT";
        }
    }

    @Override
    public List<Bill> getAllBills(String connectionType, String billStatus) {

        List<Bill> billList = new ArrayList<>();
        if (isNotBlank(connectionType) || isNotBlank(billStatus)) {
            if (isNotBlank(connectionType) && isNotBlank(billStatus)) {
                Query query = new Query();
                query.addCriteria(Criteria.where("billStatus").is(billStatus));
                List<Bill> billListWithStatus = mongoTemplate.find(query, Bill.class);
                if (!CollectionUtils.isEmpty(billListWithStatus)) {
                    billList = billListWithStatus.stream().filter(bill -> bill.getConnection().getConnectionType().equalsIgnoreCase(connectionType)).collect(Collectors.toList());
                }
            } else if (isNotBlank(billStatus)) {
                Query query = new Query();
                query.addCriteria(Criteria.where("billStatus").is(billStatus));
                billList = mongoTemplate.find(query, Bill.class);
            } else {
                List<Bill> allBills = mongoTemplate.findAll(Bill.class);
                if (!CollectionUtils.isEmpty(allBills)) {
                    billList = allBills.stream().filter(bill -> bill.getConnection().getConnectionType().equalsIgnoreCase(connectionType)).collect(Collectors.toList());
                }
            }
        } else {
            billList = mongoTemplate.findAll(Bill.class);
        }
        return billList;
    }

    private boolean isNotBlank(String str) {

        if (str != null && !str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {

        return mongoTemplate.findAll(User.class);
    }
}
