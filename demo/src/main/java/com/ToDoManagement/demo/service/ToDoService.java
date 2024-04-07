package com.ToDoManagement.demo.service;

import com.ToDoManagement.demo.dto.ToDoDto;

import java.util.List;

public interface ToDoService {
    ToDoDto addToDo(ToDoDto toDoDto);
ToDoDto getTodoById (Long toDoId);
List<ToDoDto>getAllTodos();
ToDoDto updateTodo(ToDoDto toDoDto,Long id);
void deleteTodo(Long id);

ToDoDto completeToDo(Long id);
    ToDoDto IncompleteToDo(Long id);

}
