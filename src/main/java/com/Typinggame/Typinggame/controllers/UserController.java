package com.Typinggame.Typinggame.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.Typinggame.Typinggame.models.User;
import com.Typinggame.Typinggame.service.UserService;

/**
 * UserController
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        Optional<User> retrievedUser = service.getUserById(id);
        if (retrievedUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(retrievedUser.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User addedUser = service.addUser(user);
        if (addedUser == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(addedUser, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") Long id){
        Optional<User> retrievedUser = service.getUserById(id);
        if (retrievedUser.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!service.deleteUserById(id)){
            return new ResponseEntity<>(new Exception("Could not delete User"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(retrievedUser.get(), HttpStatus.OK);
    }

    
}
