package com.ToDoManagement.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //This is a JPA class
@Table(name = "todos")
public class Todo {
    @Id //This makes the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This make the primary key as the auto increment
    private  Long id;
    @Column(name = "title",nullable = false)
    private  String title;
    @Column(nullable = false) //if we dont specify the name than JPA will create by default here description will be here
    private  String description;
    private  boolean completed;
}
