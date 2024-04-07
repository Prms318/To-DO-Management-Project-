package com.ToDoManagement.demo.controller;

import com.ToDoManagement.demo.dto.ToDoDto;
import com.ToDoManagement.demo.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor

public class TodoController {
private ToDoService toDoService;
//Now creating the ADD TO DO RESTAPI
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("addnew")
    public ResponseEntity<ToDoDto> addTodo(@RequestBody ToDoDto toDoDto){

    ToDoDto savedToDo  =    toDoService.addToDo(toDoDto);
    return  new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<ToDoDto> getTodoById(@PathVariable("id") Long todoId){
        ToDoDto toDoDto=toDoService.getTodoById(todoId);
        return  new ResponseEntity<>(toDoDto,HttpStatus.OK);
    }


    //GEt all TODOS REST API
    @GetMapping
    public ResponseEntity<List<ToDoDto>> getAllTodos(){
       List<ToDoDto> todos= toDoService.getAllTodos();
//       return  new ResponseEntity<>(todos,HttpStatus.OK);
        return  ResponseEntity.ok(todos); //this is the shortcut for this statement we can also do this
    }
// API For Updating the TODO
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ToDoDto>updateTOdo(@RequestBody ToDoDto toDoDto, @PathVariable("id") Long toDoId)
    {
       ToDoDto updatedTODO= toDoService.updateTodo(toDoDto,toDoId);
        return   ResponseEntity.ok(updatedTODO);
    }
//Build Delete TODO REST API
@PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public  ResponseEntity<String> deleteToDo(@PathVariable("id")  Long id){
        toDoService.deleteTodo(id);
        return  ResponseEntity.ok("TO DO DELETED with id :" +id);
    }
    //Build Complete TODO API
//In order to update the resources partially we use the patch mapping things
    @PatchMapping("{id}/complete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','USER')")
 public  ResponseEntity<ToDoDto> completeToDo(@PathVariable  Long id){
        ToDoDto updatedTodo= toDoService.completeToDo(id);
        return ResponseEntity.ok(updatedTodo);
 }

 //INCOmplete TODO REST API
    @PatchMapping("{id}/incomplete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','USER')")
    public  ResponseEntity<ToDoDto>incompleteToDo(@PathVariable Long id){
        ToDoDto updatedTODO= toDoService.IncompleteToDo(id);
        return  ResponseEntity.ok(updatedTODO);

    }
}
