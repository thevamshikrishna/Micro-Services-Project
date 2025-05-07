package com.telecom.repository;

import com.telecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findById(Long id);
}
