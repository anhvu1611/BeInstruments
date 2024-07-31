package com.intrument.saleintrument.web.controller;

import com.intrument.saleintrument.web.dto.request.UserCreationRequest;
import com.intrument.saleintrument.web.dto.request.UserUpdateRequest;
import com.intrument.saleintrument.web.dto.response.UserResponse;
import com.intrument.saleintrument.web.models.User;
import com.intrument.saleintrument.web.repository.OrderRepository;
import com.intrument.saleintrument.web.service.OrderService;
import com.intrument.saleintrument.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @PostMapping("/register")
    ResponseEntity<User> createUser(@RequestBody  UserCreationRequest request){
        User user = userService.createUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        Integer soDonHang = orderRepository.countOrderByUser(user);
        Double tongTien = orderService.sumTotalByUser(user);
        return new UserResponse(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName(), user.getPhoneNumber(), user.getAddress(), soDonHang, tongTien);
    }

    @DeleteMapping("/{id}")
    boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    UserResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        User user = userService.updateUser(id, request);
        Integer soDonHang = orderRepository.countOrderByUser(user);
        Double tongTien = orderService.sumTotalByUser(user);
        return new UserResponse(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName(), user.getPhoneNumber(), user.getAddress(), soDonHang, tongTien);
    }

    @GetMapping
    List<UserResponse> getAllUsers(){
        List<User> users = userService.getAllUser();
        return users.stream().map(user -> new UserResponse(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName(), user.getPhoneNumber(), user.getAddress(), orderRepository.countOrderByUser(user), orderService.sumTotalByUser(user))).toList();
    }

    @GetMapping("username/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
}
