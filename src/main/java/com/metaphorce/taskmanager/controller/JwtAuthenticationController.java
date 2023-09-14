package com.metaphorce.taskmanager.controller;

import com.metaphorce.taskmanager.model.JwtRequest;
import com.metaphorce.taskmanager.model.JwtResponse;
import com.metaphorce.taskmanager.security.services.UserDetailsServiceImpl;
import com.metaphorce.taskmanager.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        logger.info("getUsername {}", authenticationRequest.getUsername());
        logger.info("getPassword {}", authenticationRequest.getPassword());
        try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(token));

        } catch (AuthenticationException e) {

            logger.info("Autenticando al usuario: " + authenticationRequest.getUsername() + " | Autenticando al password: " + authenticationRequest.getPassword());
            logger.info(e.getMessage());
            return null;
        }
    }
}
