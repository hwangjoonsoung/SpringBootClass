package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.sound.midi.VoiceStatus;
import java.net.URI;
import java.util.Iterator;
import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }


    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable Integer id){
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException(String.format("id[%s] not found" , id));
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser= service.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void removeUser(@PathVariable int id){
        User user = service.deleteByID(id);

        if(user == null){
            throw new UserNotFoundException(String.format("id[%s] not found" , id));
        }

    }

    @PutMapping("/users/{id}")
    public void editUser(@PathVariable int id ,@RequestBody User user){
        User resultUser = service.modifyUserById(id, user);
        if (resultUser == null){
            throw new UserNotFoundException(String.format("id[%s] not found" , id));
        }

    }

}
