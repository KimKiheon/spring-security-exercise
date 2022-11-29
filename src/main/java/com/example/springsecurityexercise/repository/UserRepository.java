package com.example.springsecurityexercise.repository;

import com.example.springsecurityexercise.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
