package com.example.springsecurityexercise.service;

import com.example.springsecurityexercise.domain.dto.UserDto;
import com.example.springsecurityexercise.domain.dto.UserJoinRequest;
import com.example.springsecurityexercise.domain.entity.User;
import com.example.springsecurityexercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    public UserDto join(UserJoinRequest request){
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user ->{
                    throw new RuntimeException("UserName 중복");
                });
        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmailAddress())
                .build();
    }
}
