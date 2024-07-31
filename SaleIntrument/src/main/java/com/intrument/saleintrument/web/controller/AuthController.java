package com.intrument.saleintrument.web.controller;

import com.intrument.saleintrument.admin.service.EmailService;
import com.intrument.saleintrument.web.dto.request.AuthRequest;
import com.intrument.saleintrument.web.dto.request.UserCreationRequest;
import com.intrument.saleintrument.web.models.User;
import com.intrument.saleintrument.web.repository.RoleRepository;
import com.intrument.saleintrument.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailService emailService;


    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserCreationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setCreatedDate(LocalDate.now());
        user.setUpdatedDate(LocalDate.now());
        user.setRole(roleRepository.findByName("User"));
        authService.saveUser(user);
        // Gửi email xác nhận đăng ký thành công
        emailService.sendRegistrationConfirmation(user.getEmail(), user.getUsername());

        return ResponseEntity.ok("Người dùng đã đăng ký thành công");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            String token = authService.generateToken(authRequest.getUsername());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            return new ResponseEntity<>("Login successful", headers, HttpStatus.OK);
        } else {
            System.out.println("Invalid username or password");
            throw new RuntimeException("Invalid username or password");
        }
    }

    @PostMapping ("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            System.out.println("Token: " + authService.generateToken(authRequest.getUsername()));
            return authService.generateToken(authRequest.getUsername());
        }else {
            throw new RuntimeException("Invalid username or password");
        }
    }



//    @GetMapping("/validate")
//    public boolean validateToken(@RequestParam("token") String token) {
//        return authService.validateToken(token);
//    }


}
