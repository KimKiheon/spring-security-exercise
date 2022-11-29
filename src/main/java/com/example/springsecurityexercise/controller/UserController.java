package com.example.springsecurityexercise.controller;

import com.example.springsecurityexercise.domain.Response;
import com.example.springsecurityexercise.domain.dto.UserDto;
import com.example.springsecurityexercise.domain.dto.UserJoinRequest;
import com.example.springsecurityexercise.domain.dto.UserJoinResponse;
import com.example.springsecurityexercise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor //final이나 notnull 생성자 주입
public class UserController {
    private final UserService userService;
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest){
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(),userDto.getEmail()));
    }
}
