package com.example.agent.repository;

import com.example.agent.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    User findByAgentUsername(String username);
}
