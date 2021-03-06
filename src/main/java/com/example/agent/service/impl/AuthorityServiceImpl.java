package com.example.agent.service.impl;

import com.example.agent.model.Role;
import com.example.agent.repository.AuthorityRepository;
import com.example.agent.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Role> findById(Long id) {
        Role auth = this.authorityRepository.getOne(id);
        List<Role> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    @Override
    public List<Role> findByname(String name) {
        Role auth = this.authorityRepository.findByName(name);
        List<Role> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }
}
