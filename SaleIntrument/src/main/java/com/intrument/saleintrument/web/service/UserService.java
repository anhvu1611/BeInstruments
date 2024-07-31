package com.intrument.saleintrument.web.service;

import com.intrument.saleintrument.web.dto.request.UserCreationRequest;
import com.intrument.saleintrument.web.dto.request.UserUpdateRequest;
import com.intrument.saleintrument.web.models.User;
import com.intrument.saleintrument.web.repository.RoleRepository;
import com.intrument.saleintrument.web.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setCreatedDate(LocalDate.now());
        user.setUpdatedDate(LocalDate.now());
        user.setRole(roleRepository.findByName("user"));
        return userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean deleteUser(Long id){
        userRepository.deleteById(id);
        return true;
    }

    public User updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setUpdatedDate(LocalDate.now());
        return userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
