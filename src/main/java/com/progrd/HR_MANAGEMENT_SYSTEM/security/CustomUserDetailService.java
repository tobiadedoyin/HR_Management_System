package com.progrd.HR_MANAGEMENT_SYSTEM.security;

import com.progrd.HR_MANAGEMENT_SYSTEM.entity.Role;
import com.progrd.HR_MANAGEMENT_SYSTEM.entity.User;
import com.progrd.HR_MANAGEMENT_SYSTEM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("username not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), mapRoleToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(List<Role> roles){
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
