package com.bank.services;

import com.bank.dto.TransactionView;
import com.bank.exception.InsufficientBalance;
import com.bank.exception.UserNotFoundWithGivenId;
import com.bank.model.*;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import com.bank.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServices {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public String userLogin(String email, String password) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundWithGivenId("User not found"));
        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            return "Welcome to Bank Portal " + user.getUserName();
        } else {
            return "Invalid email or password";
        }
    }

//
//    public String registerUser(String email, String password, String userName, String address) {
//        if (userRepository.findByEmail(email).isPresent()) {
//            return "Username already exists !! plz login";
//        } else {
//            User user = new User();
//            user.setEmail(email);
//            user.setPassword(password);
//            user.setUserName(userName);
//            user.setAddress(address);
//            userRepository.save(user);
//
//            return "User registered successfully";
//        }
//    }

    public String registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Username already exists !! plz login";
        }
        userRepository.save(user);
        return "User registered successfully";
    }


    public double checkBalance(Long accountId) {
        return accountRepository.findByAccountId(accountId)
                .map(a -> a.getBalance())
                .orElseThrow(() -> new UserNotFoundWithGivenId("Account not found for id" + accountId));
    }

    @Transactional
    public void deposit(Long accountId, double amount) {
        Account acc = accountRepository.findByAccountId(accountId).orElseThrow(() -> new UserNotFoundWithGivenId("Account not found for id" + accountId));
        acc.setBalance(acc.getBalance() + amount);
        accountRepository.save(acc);
        Transaction transaction = new Transaction(TransactionType.CREDIT, LocalDateTime.now(), acc, amount, acc.getBalance(), "Money deposited to " + acc.getUser().getUserName());
        transactionRepository.save(transaction);

    }

    @Transactional
    public void withdraw(Long accountId, double amount) {
        Account acc = accountRepository.findByAccountId(accountId).orElseThrow(() -> new UserNotFoundWithGivenId("Account not found for id" + accountId));

        if (acc.getBalance() <= 0 || acc.getBalance() < amount) {
            throw new InsufficientBalance("Insufficient balance!!! ");
        }
        acc.setBalance(acc.getBalance() - amount);
        Transaction transaction = new Transaction(TransactionType.DEBIT, LocalDateTime.now(), acc, amount, acc.getBalance(), "Money withdrawn from " + acc.getUser().getUserName());
        transactionRepository.save(transaction);
        accountRepository.save(acc);

    }

    public void updateEmail(long userId, String newEmail) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundWithGivenId("User not found "));
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public void updateAddress(long userId, String newAddress) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundWithGivenId("User not found "));
        user.setAddress(newAddress);
        userRepository.save(user);

    }


    public List<TransactionView> viewTransactionSummaryById(Long accountId) {
        List<TransactionView> transactionList = transactionRepository.findTransactionsByAccountId(accountId);

        return transactionList;
    }


}
