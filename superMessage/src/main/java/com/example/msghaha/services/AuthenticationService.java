package com.example.msghaha.services;

import com.example.msghaha.mappers.UserMapper;
import com.example.msghaha.pojos.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

    @Resource
    private UserMapper userMapper;
    @Resource
    private HashService hashService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userMapper.getUser(username);
        if (user != null){
            String encodedSalt = user.getSalt();
            String hashedPassword = hashService.getHashValue(password, encodedSalt);
            if(user.getPassword().equals(hashedPassword)){
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
