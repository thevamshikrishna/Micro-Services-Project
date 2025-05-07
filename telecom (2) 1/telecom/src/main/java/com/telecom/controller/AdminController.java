package com.telecom.controller;

import com.telecom.dto.UserPlanResponseDTO;
import com.telecom.model.Admin;
import com.telecom.model.Plan;
import com.telecom.model.User;
import com.telecom.service.AdminService;
import com.telecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {
        String response = adminService.register(admin);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin) {
        String response = adminService.login(admin.getPhoneNumber(), admin.getPassword());
        return ResponseEntity.ok(response);
    }


    @PostMapping("/create/plan")
    public ResponseEntity<String> createPlan(@RequestBody Plan plan) {
        String response = adminService.createPlan(plan);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/remove/plan")
    public ResponseEntity<String> removePlan(@RequestParam Long planId) {
        String response = adminService.removePlan(planId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = adminService.viewAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        User user = adminService.viewUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("update/plan")
    public ResponseEntity<String> updatePlan(@RequestParam Long id, @RequestBody Plan plan) {
        String response = adminService.updatePlansById(id, plan);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/admin/approve-plan/{userPlanId}")
    public ResponseEntity<String> approvePlan(@PathVariable Long userPlanId) {
        String response = adminService.approvePlan(userPlanId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/userplans")
    public ResponseEntity<List<UserPlanResponseDTO>> getAllUserPlans() {
        return ResponseEntity.ok(adminService.getAllUserPlansForAdmin());
    }




}
