package com.intrument.saleintrument.admin.controller;

import com.intrument.saleintrument.web.dto.request.AuthRequest;
import com.intrument.saleintrument.web.models.User;
import com.intrument.saleintrument.web.repository.UserRepository;
import com.intrument.saleintrument.web.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/auth")
@Slf4j
public class AuthAminController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername());
        System.out.println(user.getRole().getId() + "IDDDDDDDDDDDDDD");
        if(user == null || user.getRole().getId() != 1){
            return new ResponseEntity<>("Sai username or password" , HttpStatus.OK);
        }
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
        User user = userRepository.findByUsername(authRequest.getUsername());
        if(user == null || user.getRole().getId() != 1){
            return "Invalid username or password";
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            System.out.println("Token: " + authService.generateToken(authRequest.getUsername()));
            return authService.generateToken(authRequest.getUsername());
        }else {
            return "Invalid username or password";
        }
    }

}
