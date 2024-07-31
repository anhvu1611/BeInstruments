package com.intrument.saleintrument.web.config;

import com.intrument.saleintrument.web.filter.JwtAuthenticationFilter;
import com.intrument.saleintrument.web.service.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.crypto.spec.SecretKeySpec;


@Configuration
@EnableWebSecurity
public class AuthConfig {

    private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthConfig(JWTService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Vô hiệu hóa bảo vệ CSRF
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/auth/register", "/auth/token", "/auth/validate", "/api/cart/add", "/auth/login", "/api/products/upload","/admin/auth/token", "/api/**").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        String SECRET_KEY = "NGUYENANHVUSOFTWAREARCHITECTUREANDSYSTEMNGUYENANHVU";
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HS256");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}


//@Configuration
//@EnableWebSecurity
//public class AuthConfig {
//
//    private final JWTService jwtService;
//    private final CustomUserDetailsService customUserDetailsService;
//
//    public AuthConfig(JWTService jwtService, CustomUserDetailsService customUserDetailsService) {
//        this.jwtService = jwtService;
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)  // Vô hiệu hóa bảo vệ CSRF
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/auth/register", "/auth/token", "/auth/validate", "/api/cart/add", "/auth/login", "/api/**", "/api/products/upload").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .addFilterBefore(new JwtAuthenticationFilter(jwtService, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);
//
//        // Tùy chọn: Xóa nếu không sử dụng HTTP Basic Authentication
//        // http.httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        String SECRET_KEY = "NGUYENANHVUSOFTWAREARCHITECTUREANDSYSTEMNGUYENANHVU";
//        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "HS256");
//        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS256)
//                .build();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(customUserDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//}

