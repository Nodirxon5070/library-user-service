package com.company.userservice.service.mapper;

import com.company.userservice.dto.UserDto;
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


    @Mapping(target = "cards", ignore = true)
    public abstract UserDto toDto(User user);

    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract User toEntity(UserDto dto);

    @Mapping(target = "cards", expression = "java(user.getCards().stream().map(this.cardMapper::toDtoNotUser).collect(Collectors.toSet()))")
    public abstract UserDto toDtoWithCard(User user);

    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = User.class)
    public abstract User updateUserFromDto(UserDto dto, @MappingTarget User user);



}
