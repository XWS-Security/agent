package com.example.agent.repository;

import com.example.agent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
