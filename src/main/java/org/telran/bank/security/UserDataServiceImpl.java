package org.telran.bank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.telran.bank.entity.User;
import org.telran.bank.service.UserService;

import java.util.List;

@Service
public class UserDataServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userService.getByName(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("User with login " + username + " not found");
        }
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User( //новый объект User (Spring Security)
                userEntity.getUsername(),
                userEntity.getPassword(), //BCrypt
                List.of(new SimpleGrantedAuthority("USER")));

        return user;
    }

}
