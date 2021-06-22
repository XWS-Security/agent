package com.example.agent.service.impl;

import com.example.agent.controller.dto.LogInDto;
import com.example.agent.controller.dto.UserTokenState;

public interface LogInService {

    UserTokenState logIn(LogInDto authenticationRequest);
}
