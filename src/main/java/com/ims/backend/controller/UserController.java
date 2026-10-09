package com.ims.backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @GetMapping("/me")
    public Map<String, String> getCurrentUser(Authentication authentication) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", authentication.getName());
        userInfo.put("role", authentication.getAuthorities().iterator().next().getAuthority());
        return userInfo;
    }
}
