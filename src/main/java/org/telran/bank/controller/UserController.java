package org.telran.bank.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.telran.bank.converter.Converter;
import org.telran.bank.converter.UserCreateConverter;
import org.telran.bank.dto.UserCreateDto;
import org.telran.bank.dto.UserResponseDto;
import org.telran.bank.entity.User;
import org.telran.bank.security.AuthenticationService;
import org.telran.bank.security.model.JwtAuthenticationResponse;
import org.telran.bank.security.model.SignInRequest;
import org.telran.bank.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private Converter<User, UserCreateDto, UserResponseDto> createConverter;

    @GetMapping
    public List<User> getAll() {
        List<User>all = userService.getAll();
        return all;
    }

    @PostMapping("/create")
    public UserResponseDto create(@RequestBody @Valid UserCreateDto userDto) {
        User user = createConverter.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userFromDatabase = userService.create(user);
        UserResponseDto dto = createConverter.toDto(userFromDatabase);
        return dto;
    }


    @PostMapping("login")
    public JwtAuthenticationResponse login(@RequestBody SignInRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/equals_passwords")
    public List<User> getWithEqualsPassword(@RequestBody String password) {
        return userService.getWithEqualsPasswords(password);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable (name = "id") Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}