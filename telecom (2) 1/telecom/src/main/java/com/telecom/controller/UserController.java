package com.telecom.controller;

import com.telecom.dto.UserPlanResponseDTO;
import com.telecom.model.Plan;
import com.telecom.model.User;
import com.telecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String response = userService.register(user);
         return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PostMapping("/login/user")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
      String response = userService.login(user.getPhoneNumber(), user.getPassword());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/viewAllPlans")
    public ResponseEntity<List<Plan>> viewAllPlans() {
        List<Plan> plans = userService.viewAllPlans();
        return ResponseEntity.ok(plans);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<User> deleteUser(@RequestParam Long id) {
        User user = userService.deleteUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/viewPlanById")
    public ResponseEntity<Plan> viewPlanById(@RequestParam Long id) {
        Plan plan = userService.viewPlanById(id);
        if (plan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(plan);
    }

    @PostMapping("/user/{userId}/select-plan/{planId}")
    public ResponseEntity<String> selectPlan(@PathVariable Long userId, @PathVariable Long planId)
    {
        String response = userService.selectPlan(userId, planId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/view-plan")
    public ResponseEntity<List<UserPlanResponseDTO>> getUserPlan(@PathVariable Long userId) {
        List<UserPlanResponseDTO> userPlans = userService.getUserPlanById(userId);

        return  ResponseEntity.ok(userPlans);
    }





}
