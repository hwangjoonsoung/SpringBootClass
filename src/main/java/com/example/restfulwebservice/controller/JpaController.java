package com.example.restfulwebservice.controller;

import com.example.restfulwebservice.bean.UserJPA;
import com.example.restfulwebservice.exception.UserNotFoundException;
import com.example.restfulwebservice.repository.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class JpaController {

    private UserRepository userRepository;

    public JpaController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<UserJPA> findAll(){
        List<UserJPA> all = userRepository.findAll();
        return all;
    }

    /*responseEntity controll 하는법*/
    @GetMapping("/users2")
    public ResponseEntity<List> findAll2(){
        List<UserJPA> all = userRepository.findAll();
        long count = userRepository.count();
        List list = new ArrayList();
        list.add(count);
        list.add(all);

        return ResponseEntity.ok(list);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity findId(@PathVariable int id){
        Optional<UserJPA> byId = userRepository.findById(id);
        if(!byId.isPresent()){
            throw new UserNotFoundException(String.format("id[%s] not found" , id));
        }

        EntityModel entityModel = EntityModel.of(byId.get());
        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).findAll());
        entityModel.add(webMvcLinkBuilder.withRel("all-users"));

        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PutMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody UserJPA userJPA){
        userRepository.save(userJPA);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userJPA.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

}
