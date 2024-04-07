package com.ToDoManagement.demo.service.impl;

import com.ToDoManagement.demo.dto.ToDoDto;
import com.ToDoManagement.demo.entity.Todo;
import com.ToDoManagement.demo.exception.ResourcesNotFoundException;
import com.ToDoManagement.demo.repository.ToDoRepo;
import com.ToDoManagement.demo.service.ToDoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {
private ToDoRepo toDoRepo;
private ModelMapper modelMapper;
    @Override
    public ToDoDto addToDo(ToDoDto toDoDto) {
//        Converting the TODO DTO Into TODO JPA entity
//        Save Todo JPA entity into database
//        Todo todo= new Todo();
//        todo.setTitle(toDoDto.getTitle());
//        todo.setDescription(toDoDto.getDescription());
//        todo.setCompleted(toDoDto.isCompleted());
        //TODO JPA ENtity
//            Todo savedTodo=toDoRepo.save(todo);

            //Convert Saved TODO JPA ENtity object into TODO DTO



        ////After using the model mapper we can simply do this
        Todo todo = modelMapper.map(toDoDto,Todo.class);
        Todo savedTodo= toDoRepo.save(todo);

//        ToDoDto savedTodoDTO=new ToDoDto();
//        savedTodoDTO.setId(savedTodo.getId());
//        savedTodoDTO.setTitle(savedTodoDTO.getTitle());
//        savedTodoDTO.setDescription(savedTodo.getDescription());
//        savedTodoDTO.setCompleted(savedTodo.isCompleted());

        /////To do this using the model mapper what we can do is
        ToDoDto savedTodoDTO= modelMapper.map(savedTodo,ToDoDto.class);


        return savedTodoDTO;
    }

    @Override
    public ToDoDto getTodoById(Long id) {
      //Todo todo =  toDoRepo.findById(id).get();
        Todo todo =  toDoRepo.findById(id)
                .orElseThrow(()->new ResourcesNotFoundException("TO DO NOT FOUND with id :" + id+ "THIS IS IN TO DO IMPL"));

        return modelMapper.map(todo, ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getAllTodos() {
       // return null;
        List<Todo> todos= toDoRepo.findAll();
        return  todos.stream().map((todo)->modelMapper.map(todo,ToDoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateTodo(ToDoDto toDoDto, Long id) {
      Todo todo=  toDoRepo.findById(id)
                .orElseThrow(()->new ResourcesNotFoundException("To DO not Foind with id: "+id));
      todo.setTitle(toDoDto.getTitle());
      todo.setDescription(toDoDto.getDescription());
      todo.setCompleted(toDoDto.isCompleted());
     Todo updatedTodo= toDoRepo.save(todo);

     return  modelMapper.map(updatedTodo,ToDoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        toDoRepo.findById(id).orElseThrow(()-> new ResourcesNotFoundException("TO DO NO FOund with id :"+id+ "to delete"));
        toDoRepo.deleteById(id);
    }

    @Override
    public ToDoDto completeToDo(Long id) {

      Todo todo=  toDoRepo.findById(id).orElseThrow(()-> new  ResourcesNotFoundException("To do Not found with id " +id+ "please recheck "));
      todo.setCompleted(Boolean.TRUE);
Todo updatedToDo= toDoRepo.save(todo);
return modelMapper.map(updatedToDo,ToDoDto.class);

//        return null;
    }

    @Override
    public ToDoDto IncompleteToDo(Long id) {
      Todo todo=  toDoRepo.findById(id).orElseThrow(()->new ResourcesNotFoundException(("TO DO NOT FOUND HAI")));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTODO= toDoRepo.save(todo);
        return  modelMapper.map(updatedTODO,ToDoDto.class);
        //return null;
    }


}
