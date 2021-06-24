package com.example.agent.service.impl;

import com.example.agent.controller.dto.LogInDto;
import com.example.agent.controller.dto.UserTokenState;
import com.example.agent.model.User;
import com.example.agent.repository.UserRepository;
import com.example.agent.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImpl implements LogInService {

    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public LogInServiceImpl(TokenUtils tokenUtils, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public UserTokenState logIn(LogInDto authenticationRequest) {
        User user = getUser(authenticationRequest);
        String username = user.getUsername();
        String userType = user.getClass().getSimpleName();

        String accessToken = tokenUtils.generateToken(username);
        int accessExpiresIn = tokenUtils.getExpiredIn();
        userRepository.save(user);
        return new UserTokenState(userType, accessToken, accessExpiresIn);
    }

    private User getUser(LogInDto authenticationRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (User) authentication.getPrincipal();
    }
}
