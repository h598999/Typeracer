package com.Typinggame.Typinggame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Typinggame.Typinggame.models.User;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
}
