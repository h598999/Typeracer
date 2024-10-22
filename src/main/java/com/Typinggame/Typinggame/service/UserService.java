package com.Typinggame.Typinggame.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Typinggame.Typinggame.models.User;
import com.Typinggame.Typinggame.repository.UserRepository;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired UserRepository repo;

    public User addUser(User user){
        return repo.save(user);
    }

    public Optional<User> getUserById(Long id){
        Optional<User> retrievedUser = repo.findById(id);
        return retrievedUser;
    }

    public Optional<User> updateUser(Long id, User user){
        Optional<User> oldUser = repo.findById(id);
        if (oldUser.isEmpty()){
            return Optional.empty();
        }
        User oUser = oldUser.get();
        oUser.setUsername(user.getUsername());
        oUser.setPassword(user.getPassword());
        return Optional.of(repo.save(oUser));
    }

    public boolean deleteUserById(Long id){
        repo.deleteById(id);
        return repo.findById(id).isEmpty();
    }
}
