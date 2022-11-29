package com.example.springsecurityexercise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;//에러메시지
    private T result; //성공 반환
    private static Response<Void>error(String resultCode){
        return new Response(resultCode, null);
    }
    public static<T> Response<T> success(T result){
        return new Response<>("SUCCESS",result);
    }
}
