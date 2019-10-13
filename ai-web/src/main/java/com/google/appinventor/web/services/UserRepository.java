package com.google.appinventor.web.services;

import com.google.appinventor.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE username LIKE CONCAT('%', CONCAT(:query, '%'))")
    List<User> findByUsername(@Param("query") String query);
}
