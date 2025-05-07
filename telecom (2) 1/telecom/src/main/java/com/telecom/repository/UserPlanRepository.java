package com.telecom.repository;

import com.telecom.dto.UserPlanResponseDTO;
import com.telecom.model.UserPlan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPlanRepository extends JpaRepository<UserPlan, Long> {
    List<UserPlan> findByUserId(Long userId);
  //  @Modifying
//    @Transactional
//    @Query("UPDATE UserPlan u SET u.status = :status WHERE u.id = :id")
//    void updateStatusById(String status,Long id);

//

    @Query("SELECT new com.telecom.dto.UserPlanResponseDTO(" +
            "up.id, u.name, u.email, p.validity, p.price, up.status, up.startDate, " +
            "p.dataAllowance, p.talkTime, p.smsAllowance, u.phoneNumber,up.expiryDate) " +
            "FROM UserPlan up " +
            "JOIN up.user u " +
            "JOIN up.plan p")
    List<UserPlanResponseDTO> fetchAllUserPlansForAdmin();


    @Query("SELECT new com.telecom.dto.UserPlanResponseDTO(" +
            "up.id, u.name, u.email, p.validity, p.price, up.status, up.startDate, " +
            "p.dataAllowance, p.talkTime, p.smsAllowance, u.phoneNumber,up.expiryDate) " +
            "FROM UserPlan up " +
            "JOIN up.user u " +
            "JOIN up.plan p " +
            "WHERE u.id = :userId")
    List<UserPlanResponseDTO> fetchUserPlanById(Long userId);





}
