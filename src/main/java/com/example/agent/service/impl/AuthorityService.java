package com.example.agent.service.impl;

import com.example.agent.model.Role;

import java.util.List;

public interface AuthorityService {
    List<Role> findById(Long id);
    List<Role> findByname(String name);
}
