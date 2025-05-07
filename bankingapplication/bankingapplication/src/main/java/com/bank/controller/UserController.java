package com.bank.controller;

import com.bank.dto.TransactionView;
import com.bank.model.User;
import com.bank.services.AccountServices;
import com.bank.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private AccountServices accountServices;


    @PostMapping("/login")
    public String userLogin(@RequestParam String email, @RequestParam String password) {
        return userServices.userLogin(email, password);
    }

//    @PostMapping("/register")
//    public String registerUser(@RequestParam String email, @RequestParam String password, @RequestParam String userName, @RequestParam String address) {
//        return userServices.registerUser(email, password, userName, address);
//    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userServices.registerUser(user);
    }


    //check balance
    @GetMapping("/balance/{userId}")
    public double checkBalance(@PathVariable long userId) {
        return userServices.checkBalance(userId);
    }

    // deposit amount
    @PostMapping("/deposit")
    public String deposit(@RequestParam long accountId, @RequestParam double amount) {
        userServices.deposit(accountId, amount);
        return "Amount Deposited Successfully!!";
    }

    // withdraw amount
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accountId, @RequestParam double amount) {
        userServices.withdraw(accountId, amount);
        return "Amount withdrawn successfully!!";
    }

    // update email
    @PutMapping("/updateEmail")
    public String updateEmail(@RequestParam Long userId, @RequestParam String newEmail) {
        userServices.updateEmail(userId, newEmail);
        return "email updated successfully!!";
    }

    // update address
    @PutMapping("/updateAddress")
    public String updateAddress(@RequestParam Long userId, @RequestParam String newAddress) {
        userServices.updateAddress(userId, newAddress);
        return "address updated successfully!!";
    }

    // transfer money
    @PostMapping("/transferMoney")
    public String transferMoney(@RequestParam Long senderAccountId, @RequestParam Long receiverAccountId, @RequestParam double amount) {
        accountServices.transferMoney(senderAccountId, receiverAccountId, amount);
        return "Money Transfer successfully";
    }

    // view transaction summary using account id
    @GetMapping("/transactionSummary")
    public ResponseEntity<List<TransactionView>> getTransactionSummary(@RequestParam Long accountId) {
        List<TransactionView> transactionList = userServices.viewTransactionSummaryById(accountId);
        return ResponseEntity.ok(transactionList);
    }


}
