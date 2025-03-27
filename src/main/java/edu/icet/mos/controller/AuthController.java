package edu.icet.mos.controller;


import edu.icet.mos.dto.AuthRequest;
import edu.icet.mos.dto.RegisterRequest;
import edu.icet.mos.entity.UserEntity;
import edu.icet.mos.repository.UserRepository;
import edu.icet.mos.service.impl.JwtService;
import edu.icet.mos.service.impl.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserRegistrationService userRegistrationService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        log.info("UserRegistrationService=> createUser()");
        UserEntity user = userRegistrationService.createUser(registerRequest);
        return user == null
                ? ResponseEntity.badRequest().body(
                List.of(
                        Map.of("message", "user already exist !")
                ))
                : ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserEntity userEntity = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(() -> new RuntimeException("user not found"));
        String s = jwtService.generateToken(userEntity);
        return ResponseEntity.status(HttpStatus.OK.value()).body(Map.of("token", s));
    }
}
