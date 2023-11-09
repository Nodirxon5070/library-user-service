package com.company.userservice.service.mapper;

import com.company.userservice.dto.UserDto;
import com.company.userservice.dto.UserDto.UserDtoBuilder;
import com.company.userservice.modul.User;
import com.company.userservice.modul.User.UserBuilder;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-26T20:25:18+0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.userId( user.getUserId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.email( user.getEmail() );
        userDto.gender( user.getGender() );
        userDto.phone( user.getPhone() );
        userDto.password( user.getPassword() );
        userDto.cardId( user.getCardId() );
        userDto.birthDate( user.getBirthDate() );
        userDto.username( user.getUsername() );
        userDto.enabled( user.getEnabled() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        return userDto.build();
    }

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.firstName( dto.getFirstName() );
        user.lastName( dto.getLastName() );
        user.email( dto.getEmail() );
        user.gender( dto.getGender() );
        user.phone( dto.getPhone() );
        user.password( dto.getPassword() );
        user.cardId( dto.getCardId() );
        user.birthDate( dto.getBirthDate() );
        user.username( dto.getUsername() );
        user.enabled( dto.getEnabled() );

        return user.build();
    }

    @Override
    public UserDto toDtoWithCard(User user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.userId( user.getUserId() );
        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.email( user.getEmail() );
        userDto.gender( user.getGender() );
        userDto.phone( user.getPhone() );
        userDto.password( user.getPassword() );
        userDto.cardId( user.getCardId() );
        userDto.birthDate( user.getBirthDate() );
        userDto.username( user.getUsername() );
        userDto.enabled( user.getEnabled() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.cards( user.getCards().stream().map(this.cardMapper::toDtoNotUser).collect(Collectors.toSet()) );

        return userDto.build();
    }

    @Override
    public User updateUserFromDto(UserDto dto, User user) {
        if ( dto == null ) {
            return null;
        }

        if ( dto.getFirstName() != null ) {
            user.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            user.setLastName( dto.getLastName() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getGender() != null ) {
            user.setGender( dto.getGender() );
        }
        if ( dto.getPhone() != null ) {
            user.setPhone( dto.getPhone() );
        }
        if ( dto.getPassword() != null ) {
            user.setPassword( dto.getPassword() );
        }
        if ( dto.getCardId() != null ) {
            user.setCardId( dto.getCardId() );
        }
        if ( dto.getBirthDate() != null ) {
            user.setBirthDate( dto.getBirthDate() );
        }
        if ( dto.getUsername() != null ) {
            user.setUsername( dto.getUsername() );
        }
        if ( dto.getEnabled() != null ) {
            user.setEnabled( dto.getEnabled() );
        }

        return user;
    }
}
