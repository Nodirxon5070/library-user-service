package com.company.userservice.service.mapper;

import com.company.userservice.dto.CardDto;
import com.company.userservice.dto.request.CardRequestDto;
import com.company.userservice.dto.response.CardResponseDto;
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

    public abstract Card toEntity(CardRequestDto dto);

    @Mapping(target = "user", expression = "java(this.userMapper.toDto(card.getUser()))")
    public abstract CardResponseDto toDto(Card card);

    @Mapping(target = "user", ignore = true)
    public abstract CardResponseDto toDtoNotUser(Card card);

    @Mapping(target = "user", expression = "java(userService.get(card.getUserId()).getData())")
    public abstract CardResponseDto toDtoWithUser(Card card);

    @Mapping(target = "user", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,resultType = Card.class)
    public abstract Card updateCardToDto(CardRequestDto dto, @MappingTarget Card card);

}
