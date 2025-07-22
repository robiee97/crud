package com.example.crud.service;


import com.example.crud.dto.UserDTO;
import com.example.crud.model.User;
import com.example.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public  boolean createUser(UserDTO user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return false;
        }else{
            User newUser= new User();
            newUser.setEmail(user.getEmail());
            newUser.setName(user.getName());
            userRepository.save(newUser);
            return true;
        }
    }

    public User updateUserById(Long id,UserDTO userDTO) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User userObj= user.get();
            userObj.setName(userDTO.getName());
            userObj.setEmail(userDTO.getEmail());
            userRepository.save(userObj);
            return userObj;
        }else {
            return null;
        }
    }

    public boolean deleteUserById(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
