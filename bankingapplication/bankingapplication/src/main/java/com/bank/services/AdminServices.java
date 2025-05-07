package com.bank.services;

import com.bank.exception.UserNotFoundWithGivenId;
import com.bank.model.Account;
import com.bank.model.Admin;
import com.bank.model.User;
import com.bank.repository.AccountRepository;
import com.bank.repository.AdminRepository;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServices {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountRepository accountRepository;

    public String adminLogin(String email, String password) {

        Admin admin = adminRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundWithGivenId("User not found"));
        if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
            return " Welcome to Bank Portal " + admin.getUsername() + "  Admin";
        } else {
            return "Invalid email or password";
        }
    }

    public String adminRegister(String email, String password, String username) {
        if (adminRepository.findByEmail(email).isPresent()) {
            return "Email already exists !! plz login";
        } else {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(password);
            admin.setUsername(username);
            adminRepository.save(admin);
            return "Admin registered successfully";

        }
    }

    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }


    public User addNewUser(User newUser) {

        for (Account account : newUser.getAccounts()) {
            account.setUser(newUser); // This is KEY: manually set the user reference in Account
        }
        return userRepo.save(newUser);
    }

    public List<User> getAllUserNames() {
        List<User> all = userRepo.findAll();
        return all;
    }

    public User getUserWithAccounts(Long userId) {
        return userRepo.findUserWithAccounts(userId)
                .orElseThrow(() -> new UserNotFoundWithGivenId("User not found"));
    }

    public void removeUserByAccountId(Long accountId) {
        userRepo.deleteById(accountId);
    }


}
