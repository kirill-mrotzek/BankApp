package org.telran.bank.security;

import org.telran.bank.security.model.JwtAuthenticationResponse;
import org.telran.bank.security.model.SignInRequest;

public interface AuthenticationService {

    JwtAuthenticationResponse authenticate(SignInRequest request);

}
