package com.intrument.saleintrument.web.service;

import com.intrument.saleintrument.web.models.User;
import com.intrument.saleintrument.web.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
@RequiredArgsConstructor

public class AuthService {
    @Getter
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;

    public ResponseEntity<String> saveUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            // Nếu username đã tồn tại, trả về một thông báo lỗi dưới dạng JSON
            String errorMessage = "Username already exists";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Lưu người dùng mới vào cơ sở dữ liệu
        userRepository.save(user);
        // Tạo token cho người dùng
        String token = jwtService.generateToken(user.getUsername());
        System.out.println("Token: " + token);

        return ResponseEntity.ok("User saved successfully");
    }


    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

//    public boolean validateToken(String token) {
//        return jwtService.validateToken(token, userDetails);
//    }
}
