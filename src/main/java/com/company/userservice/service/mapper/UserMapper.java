package com.company.userservice.service.mapper;

import com.company.userservice.dto.UserDto;
import com.company.userservice.dto.request.UserRequestDto;
import com.company.userservice.dto.response.UserResponseDto;
import com.company.userservice.modul.User;
import com.company.userservice.service.CardService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring" , imports = Collectors.class)
public abstract class UserMapper {


    @Lazy
    @Autowired
    protected CardService cardService;

    @Lazy
    @Autowired
    protected CardMapper cardMapper;


    public abstract UserResponseDto toDto(User user);


    public abstract User toEntity(UserRequestDto dto);

    @Mapping(target = "cards", expression = "java(user.getCards().stream().map(this.cardMapper::toDtoNotUser).collect(Collectors.toSet()))")
    public abstract UserResponseDto toDtoWithCard(User user);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = User.class)
    public abstract User updateUserFromDto(UserRequestDto dto, @MappingTarget User user);



}
