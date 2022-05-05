package com.hnm.billing.controller;

import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.model.BillStatus;
import com.hnm.billing.model.Connection;
import com.hnm.billing.model.ConnectionType;
import com.hnm.billing.model.Supplier;
import com.hnm.billing.model.User;
import com.hnm.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public Bill saveBill(@RequestParam String userId, @RequestParam String billingDate, @RequestParam String connectionId, @RequestParam String amount) throws ParseException {
        Connection connection = billService.getConnectionById(Integer.valueOf(connectionId));
        Bill bill = new Bill();
        bill.setUserId(Integer.valueOf(userId));
        bill.setBillStatus(BillStatus.PENDING);
        bill.setBillingDate(billingDate);
        bill.setAmount(Double.valueOf(amount));
        bill.setConnection(connection);
        return billService.saveBill(bill);
    }

    @GetMapping("/getSuppliersByConnectionType/{connectionType}")
    @ResponseBody
    public List<Supplier> getSuppliersByConnectionType(@PathVariable String connectionType){
       return billService.getSuppliersByConnectionType(connectionType);
    }

    @GetMapping("/saveConnection")
    @ResponseBody
    public ResponseEntity<Connection> saveConnection(@RequestParam String connectionType, @RequestParam String connectionNumber, @RequestParam String supplierId, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        Connection connection = new Connection();
        connection.setUserId(currentUser.getId());
        connection.setConnectionType(ConnectionType.valueOf(connectionType).getDisplayName());
        connection.setStatus(true);
        try {
            Connection savedConnection = billService.saveConnection(connection, Long.parseLong(supplierId));
            return new ResponseEntity<>(savedConnection, HttpStatus.OK);
        } catch(Exception ex){
            if(ex.getMessage().equalsIgnoreCase("DUPLICATE_CONNECTION")){
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
            }
        }
    }

    @GetMapping("/getConnectionsByUserId")
    @ResponseBody
    public List<Connection> getConnectionsByUserId(HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null) {
            return billService.getConnectionsByUserId(currentUser.getId());
        } else {
            return null;
        }
    }

    @GetMapping("/getBillsByUserIdAndConnectionType/{connectionType}")
    @ResponseBody
    public List<Bill> getBillsByUserIdAndConnectionType(@PathVariable String connectionType, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null){
           return billService.getBillsByUserIdAndConnectionType(currentUser.getId(), connectionType);
        } else {
            return null;
        }
    }

    @PutMapping("/payBill/{billId}")
    @ResponseBody
    public String payBill(@PathVariable long billId){
       return billService.payBill(billId);
    }
}
