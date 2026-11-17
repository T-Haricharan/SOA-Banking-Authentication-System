package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    @GetMapping("/balance")
    public String getBalance() {
        return "Account Balance: Rs. 50000";
    }

    @GetMapping("/details")
    public String getDetails() {
        return "Account Holder: Hari Charan";
    }

    @PostMapping("/transfer")
    public String transfer() {
        return "Amount Transferred Successfully";
    }
}