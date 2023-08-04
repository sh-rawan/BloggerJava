package com.agrawals.blogger.service;

import com.agrawals.blogger.dto.LoginDto;
import com.agrawals.blogger.dto.RegisterDto;

public interface AuthServiceInter {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
