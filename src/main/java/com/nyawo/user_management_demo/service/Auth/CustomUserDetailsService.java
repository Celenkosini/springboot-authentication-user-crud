package com.nyawo.user_management_demo.service.Auth;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.nyawo.user_management_demo.model.User;
import com.nyawo.user_management_demo.repository.UserRepository;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
             user.getPassword(),
             new ArrayList<>());
    }
    
}
