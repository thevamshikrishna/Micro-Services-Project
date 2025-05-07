package com.mentorassignment.RetailApplication.controller;


import com.mentorassignment.RetailApplication.model.User;
import com.mentorassignment.RetailApplication.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get")
    public List<User> showUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable("id") String id,User user){
        userService.saveUser(user);
    }

    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }



}
