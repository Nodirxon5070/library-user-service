package com.company.userservice.service.mapper;

import com.company.userservice.dto.CardDto;
import com.company.userservice.modul.Card;
import com.company.userservice.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class CardMapper {
    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected UserService userService;
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract Card toEntity(CardDto dto);

    @Mapping(target = "user", expression = "java(this.userMapper.toDto(card.getUser()))")
    public abstract CardDto toDto(Card card);

    @Mapping(target = "user", ignore = true)
    public abstract CardDto toDtoNotUser(Card card);

    @Mapping(target = "user", expression = "java(userService.get(card.getUserId()).getData())")
    public abstract CardDto toDtoWithUser(Card card);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateCardToDto(CardDto dto, @MappingTarget Card card);

}
