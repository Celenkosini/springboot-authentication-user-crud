package com.nyawo.user_management_demo.controller;

import com.nyawo.user_management_demo.service.UserService;
import com.nyawo.user_management_demo.DTO.UserDTO;
import com.nyawo.user_management_demo.model.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
   
    
    UserService userService;
    User user;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    //get all users
     @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    //create a user
     @PostMapping
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "user created";
    }
    //update a user
     @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id,@RequestBody User user) {
        return userService.updateUser(id, user);
    }
    //delete a user
     @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    //get a user by id
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO != null) {
            return userDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
    //get a user by email
    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable("email") String email) {
        UserDTO userDTO = userService.getUserByEmail(email);
        if (userDTO != null) {
            return userDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
    //get a user by username
    @GetMapping("/username/{username}")
    public UserDTO getUserByUsername(@PathVariable("username") String username) {
        UserDTO userDTO = userService.getUserByUsername(username);
        if (userDTO != null) {
            return userDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
