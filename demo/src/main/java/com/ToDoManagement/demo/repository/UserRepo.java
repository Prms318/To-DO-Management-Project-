package com.ToDoManagement.demo.repository;

import com.ToDoManagement.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User>findByUsername(String username);


    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username,String email);

}
