package com.hnm.billing.dao.impl;

import com.hnm.billing.dao.BillDao;
import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.*;
import com.hnm.billing.service.impl.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
            List<Connection> connectionList = mongoTemplate.find(query, Connection.class);
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
}
