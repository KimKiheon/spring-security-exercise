package com.example.springsecurityexercise.service;

import com.example.springsecurityexercise.domain.dto.UserDto;
import com.example.springsecurityexercise.domain.dto.UserJoinRequest;
import com.example.springsecurityexercise.domain.entity.User;
import com.example.springsecurityexercise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDto join(UserJoinRequest request){
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user ->{
                    throw new RuntimeException("UserName 중복");
                });
        User savedUser = userRepository.save(request.toEntity());
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .emailAddress(savedUser.getEmailAddress())
                .build();
    }
}
