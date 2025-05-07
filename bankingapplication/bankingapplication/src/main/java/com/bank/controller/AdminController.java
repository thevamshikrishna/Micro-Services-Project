package com.bank.controller;

import com.bank.model.AccountType;
import com.bank.model.Admin;
import com.bank.model.User;
import com.bank.services.AccountServices;
import com.bank.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServices adminService;

    @Autowired
    private AccountServices accountServices;

    @PostMapping("/login")
    public String adminLogin(@RequestParam String email, @RequestParam String password) {
        return adminService.adminLogin(email, password);
    }

    @PostMapping("/register")
    public String adminRegister(@RequestParam String email, @RequestParam String password, @RequestParam String username) {
        return adminService.adminRegister(email, password, username);
    }

    // Admin able to view all users accounts
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUserNames();
    }

    // Admin able to add Admin accounts
    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.addAdmin(admin));
    }

    // Admin able to add user accounts
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(adminService.addNewUser(user));
    }

    // Admin able to view user accounts with user
    @GetMapping("/with/accounts/{userId}")
    public ResponseEntity<User> getUserWithAccounts(@PathVariable Long userId) {
        User user = adminService.getUserWithAccounts(userId);
        return ResponseEntity.ok(user);
    }

    // Admin able to remove user accounts
    @DeleteMapping("/remove/{accountId}")
    public String removeUser(@PathVariable Long accountId) {
        adminService.removeUserByAccountId(accountId);
        return "User with Account ID " + accountId + " removed.";
    }

    @PostMapping("/addAccountToUser/{userId}")
    public String addAccountToUserWithId(@PathVariable Long userId, @RequestParam String accountType) {
        accountServices.activeAccountForUserById(userId, accountType);
        return "Account added successfully to user with ID " + userId;
    }
}
