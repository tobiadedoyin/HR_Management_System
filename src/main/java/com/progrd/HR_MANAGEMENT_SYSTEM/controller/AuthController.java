package com.progrd.HR_MANAGEMENT_SYSTEM.controller;

import com.progrd.HR_MANAGEMENT_SYSTEM.dto.AuthResponseDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.LoginDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.dto.RegisterDto;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Role;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.User;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.RoleRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.UserRepository;
import com.progrd.HR_MANAGEMENT_SYSTEM.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if(userRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("username is taken", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return new ResponseEntity<>("user details saved successfully",
                HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return  new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }
}
