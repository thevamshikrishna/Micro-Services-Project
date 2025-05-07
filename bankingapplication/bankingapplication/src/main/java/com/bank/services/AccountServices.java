package com.bank.services;

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
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AccountServices {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;


    Logger logger

            = Logger.getLogger(AccountServices.class.getName());

    @Transactional
    public void transferMoney(Long senderAccountId, Long receiverAccountId, double amount) {
        Account senderAccount = accountRepository.findById(senderAccountId)
                .orElseThrow(() -> new UserNotFoundWithGivenId("Sender account not found"));

        Account receiverAccount = accountRepository.findById(receiverAccountId)
                .orElseThrow(() -> new UserNotFoundWithGivenId("Receiver account not found"));
        logger.warning("Sender account: " + senderAccount );

        if (senderAccount.getBalance() < amount) {
            throw new InsufficientBalance("Not enough balance to transfer");
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);


        Long senderId = senderAccount.getAccountId();
        Long receiverId = receiverAccount.getAccountId();

        String senderUserName = senderAccount.getUser().getUserName();
        String receiverUserName = receiverAccount.getUser().getUserName();

//create  transaction for sender and receiver
        Transaction senderTransaction = new Transaction(TransactionType.TRANSFER, LocalDateTime.now(), senderAccount, amount, senderAccount.getBalance(), senderUserName + " transferred money to : " + receiverUserName);
        Transaction receiverTransaction = new Transaction(TransactionType.RECEIVE, LocalDateTime.now(), receiverAccount, amount, receiverAccount.getBalance(), receiverUserName + " received money from : " + senderUserName);

        // Save the transactions to the database
        transactionRepository.save(receiverTransaction);
        transactionRepository.save(senderTransaction);

        System.out.println(senderTransaction);

        // Save the updated accounts to the database
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

    }

    public void activeAccountForUserById(long userId, String accountType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundWithGivenId("User not found"));

        Account account = new Account();
        account.setUser(user);
        account.setBalance(0.0);
        account.setAccountType(AccountType.valueOf(accountType)); // Ensure enum is valid
        accountRepository.save(account);
    }

}
