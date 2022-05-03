package com.hnm.billing.controller;

import com.hnm.billing.dto.ConnectionDTO;
import com.hnm.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/generateBill/{userId}")
    public ConnectionDTO generateBill(@PathVariable("userId") long userId){
        return billService.generateBill(userId);
    }
}
