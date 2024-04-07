package com.ToDoManagement.demo.repository;

import com.ToDoManagement.demo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepo extends JpaRepository<Todo,Long> {

}
