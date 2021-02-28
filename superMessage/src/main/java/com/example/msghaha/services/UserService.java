package com.example.msghaha.services;

import com.example.msghaha.mappers.UserMapper;
import com.example.msghaha.pojos.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private HashService hashService;

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public User getUser(String username){
        return userMapper.getUser(username);
    }
}
