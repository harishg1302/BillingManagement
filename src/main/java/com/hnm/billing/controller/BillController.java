package com.hnm.billing.controller;

import com.hnm.billing.dto.BillDTO;
import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.*;
import com.hnm.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/generateBill/{userId}")
    @ResponseBody
    public ConnectionDTO generateBill(@PathVariable("userId") long userId){
        return billService.generateBill(userId);
    }

    private ConnectionDTO buildConnectionDto(){
        ConnectionDTO connectionDTO = new ConnectionDTO();
        connectionDTO.setEmailId("h@g.com");
        Connection connection = new Connection();
        connection.setId(1);
        connection.setUserId(2);
        connection.setStatus(true);
        connection.setConnectionType(ConnectionType.DTH);
        connection.setConnectionNumber("123456789");
        Supplier supplier = new Supplier();
        supplier.setId(1);
        supplier.setName("TATA SKY");
        supplier.setStatus(true);
        supplier.setAmount(500);
        connection.setSupplier(supplier);

        Connection connection1 = new Connection();
        connection.setId(1);
        connection.setUserId(2);
        connection.setStatus(true);
        connection.setConnectionType(ConnectionType.DTH);
        connection.setConnectionNumber("123456789");
        Supplier supplier1 = new Supplier();
        supplier.setId(1);
        supplier.setName("TATA SKY");
        supplier.setStatus(true);
        supplier.setAmount(500);
        connection.setSupplier(supplier1);

        List<Connection> connectionList = new ArrayList<>();
        connectionList.add(connection);
        connectionList.add(connection1);
        connectionDTO.setConnectionList(connectionList);
        return connectionDTO;

    }

    @PostMapping("/saveBill")
    @ResponseBody
    public Bill saveBill(@RequestBody BillDTO billDTO){
        Connection connection = billService.getConnectionById(billDTO.getConnectionId());
        Bill bill = new Bill();
        bill.setUserId(billDTO.getUserId());
        bill.setBillStatus(BillStatus.PENDING);
        bill.setBillingDate(billDTO.getBillingDate());
        bill.setAmount(billDTO.getAmount());
        bill.setConnection(connection);
        return billService.saveBill(bill);
    }
}
