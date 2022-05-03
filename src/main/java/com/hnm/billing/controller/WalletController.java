package com.hnm.billing.controller;

import com.hnm.billing.model.User;
import com.hnm.billing.model.Wallet;
import com.hnm.billing.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/getByUserId")
    @ResponseBody
    public Wallet getWalletForCurrentUser(HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null){
            return walletService.getWalletByUserId(currentUser.getId());
        } else {
            return null;
        }
    }

    @PutMapping("/updateBalance/{amount}")
    @ResponseBody
    public Wallet getWalletWithUpdatedBalance(@PathVariable("amount") double addAmount, HttpSession session){
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null){
            return walletService.getWalletWithUpdatedBalance(currentUser.getId(), addAmount);
        } else {
            return null;
        }
    }

}
