package org.example.signupwithsecurity.controller;


import org.example.signupwithsecurity.dto.AuthRequest;
import org.example.signupwithsecurity.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final WebApplicationContext webApplicationContext;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, WebApplicationContext webApplicationContext) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.webApplicationContext = webApplicationContext;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        Authentication authRequestTocken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authRequestTocken);

        if (authentication.isAuthenticated()) {
            return jwtService.generateTocken(authentication);
        }
        else {
            throw new BadCredentialsException("Invalid username or password");
        }
    }


}