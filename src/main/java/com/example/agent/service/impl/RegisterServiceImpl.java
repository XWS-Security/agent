package com.example.agent.service.impl;

import com.example.agent.controller.dto.RegisterDto;
import com.example.agent.exceptions.*;
import com.example.agent.mail.AccountActivationLinkMailFormatter;
import com.example.agent.mail.MailService;
import com.example.agent.model.Agent;
import com.example.agent.model.Role;
import com.example.agent.model.User;
import com.example.agent.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.net.ssl.SSLException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final AuthorityService authService;
    private final PasswordEncoder passwordEncoder;
    private final MailService<String> mailService;

//    @Value("${CONTENT}")
//    private String contentMicroserviceURI;
//
//    @Value("${FOLLOWER}")
//    private String followerMicroserviceURI;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository,
                               AuthorityService authService,
                               PasswordEncoder passwordEncoder,
                               MailService<String> mailService) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public Agent register(RegisterDto dto, String siteURL) throws MessagingException, PasswordIsNotValid, SSLException {

        if (!dto.getPassword().equals(dto.getRepeatedPassword())) {
            throw new PasswordsDoNotMatch();
        }

        Agent user = new Agent();
        List<Role> auth = authService.findByname(user.getAdministrationRole());

        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRoles(auth);
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRegistrationSentDate(new Timestamp(System.currentTimeMillis()));
        user.setGender(dto.getGender());

        String activationCode = RandomString.make(64);
        user.setActivationCode(activationCode);

        if (userExists(user.getEmail(), user.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        user = userRepository.save(user);
        sendActivationLink(user, siteURL);
        return user;
    }

    @Override
    public Agent activate(String email, String activationCode) throws BadActivationCodeException {
        Agent user = findByEmail(email);
        if (!user.getActivationCode().equals(activationCode)) {
            userRepository.delete(user);
            throw new BadActivationCodeException();
        }

        user.Enable();
        user.setActivationCode(null);
        user = this.userRepository.save(user);
        return user;
    }

    @Override
    public Agent findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        } else {
            return (Agent) user;
        }
    }

    @Override
    public boolean userExists(String email, String username) {
        try {
            return findByEmail(email) != null;
        } catch (Exception e) {
            return true;
        }
    }

    private void sendActivationLink(Agent agent, String siteUrl) throws MessagingException {
        String verifyURL = siteUrl + "/activation?code=" + agent.getActivationCode() + "&email=" + agent.getEmail();
        mailService.sendMail(agent.getEmail(), verifyURL, new AccountActivationLinkMailFormatter());
    }
}
