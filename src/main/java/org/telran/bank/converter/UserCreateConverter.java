package org.telran.bank.converter;

import org.springframework.stereotype.Component;
import org.telran.bank.dto.UserCreateDto;
import org.telran.bank.dto.UserResponseDto;
import org.telran.bank.entity.User;

@Component
public class UserCreateConverter implements Converter <User, UserCreateDto, UserResponseDto>{

    @Override
    public UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername());
        //поля из объекта user
    }

    @Override
    public User toEntity(UserCreateDto userCreateDto) {
        return new User(userCreateDto.getUsername(), userCreateDto.getPassword());
    }
}
