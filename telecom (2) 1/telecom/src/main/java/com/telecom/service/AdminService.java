package com.telecom.service;

import com.telecom.exceptionhandling.UserNotFound;
import com.telecom.model.UserPlan;
import com.telecom.dto.UserPlanResponseDTO;
import com.telecom.exceptionhandling.PlanNotFound;
import com.telecom.exceptionhandling.RegisterLoginInValid;
import com.telecom.model.*;
import com.telecom.repository.AdminRepository;
import com.telecom.repository.PlanRepository;
import com.telecom.repository.UserPlanRepository;
import com.telecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.telecom.exceptionhandling.UserPlanNotFound;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepo;
    @Autowired
    PlanRepository planRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPlanRepository userPlanRepository;

    @Autowired(required = true)
    private BCryptPasswordEncoder passwordEncoder;

    Logger logger = Logger.getLogger(AdminService.class.getName());

    // etc 914567 // recharg => number ==> dubplicate number unique
    public String register(Admin admin) {
        if (adminRepo.findByPhoneNumber(admin.getPhoneNumber()).isPresent()) {
            logger.warning("Admin phoneNumber already registered");
            return "Admin phoneNumber already registered";
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepo.save(admin);

        logger.info("Admin registered successfully");
        return "Admin registered successfully";
    }

    public String login(String phoneNumber, String password) {
        Optional<Admin> adminOpt = adminRepo.findByPhoneNumber(phoneNumber);

        Admin admin = adminOpt.filter(a -> passwordEncoder.matches(password, a.getPassword()))
                .orElseThrow(() -> new RegisterLoginInValid("Invalid admin credentials"));

        return "Admin login successful";
    }


    public String createPlan(Plan plan) {
        planRepository.save(plan);
        logger.info("Plan created successfully");
        return "Plan created successfully";
    }

    public String removePlan(Long planId) {
        if (planRepository.existsById(planId)) {
            planRepository.deleteById(planId);
            logger.info("Plan removed successfully");
            return "Plan removed successfully";
        } else {
            logger.warning("Plan not found");
            throw new PlanNotFound("Plan with this " + planId + " not found");

        }
    }

    //View users information
    public List<User> viewAllUsers() {
        List<User> allUser = userRepository.findAll();

        if (allUser.isEmpty()) {
            logger.info("No users found");
        }
        return allUser;
    }

    public User viewUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFound("User with this id "+userId+ " not found "));
    }

    public String updatePlansById(Long id, Plan plan) {
        if (planRepository.existsById(id)) {
            Plan existingPlan = planRepository.findById(id).orElseThrow(() -> new PlanNotFound("Plan with this id not found"));
            existingPlan.setValidity(plan.getValidity());
            existingPlan.setPrice(plan.getPrice());
            existingPlan.setDataAllowance(plan.getDataAllowance());
            existingPlan.setTalkTime(plan.getTalkTime());
            existingPlan.setSmsAllowance(plan.getSmsAllowance());

            planRepository.save(existingPlan);
            logger.info("Plan updated successfully");
            return "Plan updated successfully";
        } else {
            logger.warning("Plan not found");
            throw new PlanNotFound("Plan with this " + id + " not found");

        }
    }

    public String approvePlan(Long userPlanId) {

        if (userPlanRepository.existsById(userPlanId)) {
            UserPlan userPlan = userPlanRepository.findById(userPlanId).orElseThrow(() -> new UserPlanNotFound("User plan with this id not found"));
            //userPlan.setStatus("APPROVED");
            userPlan.setStatus(PlanStatus.APPROVED.toString());
            userPlanRepository.save(userPlan);
            logger.info("Plan approved successfully");
            return "Plan approved successfully";
        } else {
            logger.warning("User plan not found");
            throw new PlanNotFound("Plan with this UserPlanId " + userPlanId + " not found");

        }

    }

    public List<UserPlanResponseDTO> getAllUserPlansForAdmin() {
        List<UserPlanResponseDTO> userPlans = userPlanRepository.fetchAllUserPlansForAdmin();
        if (userPlans.isEmpty()) {
            logger.info("No user plans found");
            throw new UserPlanNotFound("No user plans found");

        }
        return userPlanRepository.fetchAllUserPlansForAdmin();
    }


//    Public List<Plan> viewAllPlans()
//    {
//        List<Plan> allPlans = planRepository.findAll();
//        if(allPlans.isEmpty())
//        {
//            logger.info("No plans found");
//        }
//        return allPlans;
//    }


}
