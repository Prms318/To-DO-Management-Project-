package com.ToDoManagement.demo.repository;

import com.ToDoManagement.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {

}
