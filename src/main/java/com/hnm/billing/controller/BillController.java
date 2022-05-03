package com.hnm.billing.controller;

import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.model.BillStatus;
import com.hnm.billing.model.Connection;
import com.hnm.billing.model.Supplier;
import com.hnm.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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

    @GetMapping("/saveBill")
    @ResponseBody
    public Bill saveBill(@RequestParam String userId, @RequestParam String billingDate, @RequestParam String connectionId, @RequestParam String amount) {
        Connection connection = billService.getConnectionById(Integer.valueOf(connectionId));
        Bill bill = new Bill();
        bill.setUserId(Integer.valueOf(userId));
        bill.setBillStatus(BillStatus.PENDING);
        bill.setBillingDate(new Date(billingDate));
        bill.setAmount(Double.valueOf(amount));
        bill.setConnection(connection);
        return billService.saveBill(bill);
    }

    @GetMapping("/getSuppliersByConnectionType")
    @ResponseBody
    public List<Supplier> getSuppliersByConnectionType(@RequestParam String connectionType){
       return billService.getSuppliersByConnectionType(connectionType);
    }
}
