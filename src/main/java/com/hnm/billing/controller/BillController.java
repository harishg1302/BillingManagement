package com.hnm.billing.controller;

import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.model.Bill;
import com.hnm.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/saveBill")
    @ResponseBody
    public Bill saveBill(@RequestBody Bill bill){
        return billService.saveBill(bill);
    }
}
