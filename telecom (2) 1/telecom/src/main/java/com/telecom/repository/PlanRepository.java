package com.telecom.repository;

import com.telecom.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,Long> {

}
