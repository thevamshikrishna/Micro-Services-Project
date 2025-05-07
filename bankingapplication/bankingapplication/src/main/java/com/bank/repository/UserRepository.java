package com.bank.repository;


import com.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.accounts WHERE u.userId = :userId")
    Optional<User> findUserWithAccounts(@Param("userId") Long userId);


    Optional<User> findByEmail(@Param("email") String email);
}
