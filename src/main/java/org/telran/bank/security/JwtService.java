package org.telran.bank.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken(UserDetails userDetails) {
        return null;
    }

    public String extractUserName(String jwt) {
        return null;
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        return false;
    }
}
