package com.telecom.service;

import com.telecom.dto.UserPlanResponseDTO;
import com.telecom.exceptionhandling.PlanNotFound;
import com.telecom.exceptionhandling.RegisterLoginInValid;
import com.telecom.exceptionhandling.UserNotFound;
import com.telecom.exceptionhandling.UserPlanNotFound;
import com.telecom.model.Plan;
import com.telecom.model.PlanStatus;
import com.telecom.model.User;
import com.telecom.model.UserPlan;
import com.telecom.repository.PlanRepository;
import com.telecom.repository.UserPlanRepository;
import com.telecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserService {

    @Autowired
    UserPlanRepository userPlanRepository;
    @Autowired
    UserRepository userRepo;
    @Autowired
    PlanRepository planRepository;
    Logger logger = Logger.getLogger(UserService.class.getName());

    public String register(User user) {
        if (userRepo.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            logger.warning("User PhoneNumber already registered");
            return "User PhoneNumber already registered";
        }

        userRepo.save(user);
        logger.info("User registered successfully");
        return "User registered successfully";
    }

    public String login(String PhoneNumber, String password) {
        return userRepo.findByPhoneNumber(PhoneNumber)
                .filter(u -> u.getPassword().equals(password) && u.getPhoneNumber().equals(PhoneNumber))
                .map(u -> "Welcome " + u.getName())
                .orElseThrow(() -> new RegisterLoginInValid("Invalid user credentials"));
    }


    public List<Plan> viewAllPlans() {
        if(planRepository.findAll().isEmpty()) {
            throw new PlanNotFound("No Plans Available");
        }
        return planRepository.findAll();
    }

    public User deleteUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFound("Invalid User Id"));
        userRepo.deleteById(id);
        return user;
    }

    public Plan viewPlanById(Long id) {
        return planRepository.findById(id).orElseThrow(() -> new PlanNotFound("Invalid Plan Id"));

    }


    public String selectPlan(Long userId, Long planId) {
        // Validate user and plan
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFound("Invalid User Id"));
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFound("Invalid Plan Id"));

        // Create and save UserPlan
        UserPlan userPlan = new UserPlan();
        userPlan.setUser(user);
        userPlan.setPlan(plan);
        userPlan.setStatus(PlanStatus.PENDING.toString());  // By default, waiting for admin approval
        userPlan.setStartDate(LocalDate.now());

        // Calculate expiry date based on plan validity
        // "365" => long parse + plus days
        String validity = plan.getValidity().replaceAll("[^0-9]", ""); // Extract only numbers
        long days = Long.parseLong(validity);
        userPlan.setExpiryDate(LocalDate.now().plusDays(days));
        userPlanRepository.save(userPlan);

        logger.info("Plan selected successfully and is pending approval.");
        return "Plan selected successfully and is pending approval.";
    }

    public List<UserPlanResponseDTO> getUserPlanById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFound("Invalid User Id"));
        return userPlanRepository.fetchUserPlanById(userId);

    }


}
