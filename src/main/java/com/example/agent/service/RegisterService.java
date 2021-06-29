package com.example.agent.service;

import com.example.agent.controller.dto.RegisterDto;
import com.example.agent.exceptions.BadActivationCodeException;
import com.example.agent.model.Agent;
import com.example.agent.model.User;

import javax.mail.MessagingException;
import javax.net.ssl.SSLException;

public interface RegisterService {
    User activate(String email, String activationCode) throws BadActivationCodeException;

    User register(RegisterDto registerDto, String siteURL) throws MessagingException, SSLException;

    Agent findByEmail(String email);

    boolean userExists(String email, String username);
}
