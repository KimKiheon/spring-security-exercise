package com.example.springsecurityexercise.service;

import com.example.springsecurityexercise.domain.dto.UserDto;
import com.example.springsecurityexercise.domain.dto.UserJoinRequest;
import com.example.springsecurityexercise.domain.entity.User;
import com.example.springsecurityexercise.exception.ErrorCode;
import com.example.springsecurityexercise.exception.HospitalReviewAppException;
import com.example.springsecurityexercise.repository.UserRepository;
import com.example.springsecurityexercise.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String secretKey;

    public UserDto join(UserJoinRequest request) {
        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new RuntimeException("UserName 중복");
                });
        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmailAddress())
                .build();
    }

    public String login(String userName, String password) {
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new HospitalReviewAppException(ErrorCode.NOT_FOUND,
                        String.format("%s는 가입되지 않은 userName입니다")));
        if (!encoder.matches(password, user.getPassword())) {
            throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD, "password가 올바르지 않습니다");
        }
        return JwtTokenUtil.createToken(userName, secretKey, 1000 * 60 * 60);

    }
}
