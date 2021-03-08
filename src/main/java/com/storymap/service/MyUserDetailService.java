package com.storymap.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface MyUserDetailService {
    UserDetails loadUserByname(String username);
}

