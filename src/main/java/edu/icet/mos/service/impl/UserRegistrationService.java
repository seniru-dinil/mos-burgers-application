package edu.icet.mos.service.impl;


import edu.icet.mos.dto.RegisterRequest;
import edu.icet.mos.entity.UserEntity;
import edu.icet.mos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity createUser(RegisterRequest request){
        Optional<UserEntity> userEntity = userRepository.findByEmail(request.getEmail());
        if(userEntity.isPresent()){
            return null;
        }
        return userRepository.save(UserEntity.builder()
                .id(request.getId())
                .email(request.getEmail())
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
    }
}
