package com.example.crud.controller;

import com.example.crud.dto.UserDTO;
import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/hello")
    @ResponseBody // This annotation tells Spring to return the string directly as the response body
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user){
        boolean isCreated = userService.createUser(user);
        if(isCreated){
            return new ResponseEntity<>("user is created successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not created",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserById(@PathVariable Long id , @RequestBody UserDTO userDTO){
        User user = userService.updateUserById(id,userDTO);
        if (user!=null){
            return new ResponseEntity<>("User has been Updated" + user.toString(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("User not Found", HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        boolean isDeleted = userService.deleteUserById(id);
        if(isDeleted){
            return new ResponseEntity<>("User has been deleted with Id :"+id, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No User Found", HttpStatus.BAD_REQUEST);
        }
    }
}
