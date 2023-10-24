package com.company.userservice.service.mapper;

import com.company.userservice.dto.CardDto;
import com.company.userservice.dto.CardDto.CardDtoBuilder;
import com.company.userservice.modul.Card;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-24T15:19:16+0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 17.0.8 (Amazon.com Inc.)"
)
@Component
public class CardMapperImpl extends CardMapper {

    @Override
    public Card toEntity(CardDto dto) {
        if ( dto == null ) {
            return null;
        }

        Card card = new Card();

        card.setCardName( dto.getCardName() );
        card.setCardNumber( dto.getCardNumber() );
        card.setUserId( dto.getUserId() );

        return card;
    }

    @Override
    public CardDto toDto(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDtoBuilder cardDto = CardDto.builder();

        cardDto.cardId( card.getCardId() );
        cardDto.cardName( card.getCardName() );
        cardDto.cardNumber( card.getCardNumber() );
        cardDto.userId( card.getUserId() );
        cardDto.updatedAt( card.getUpdatedAt() );
        cardDto.deletedAt( card.getDeletedAt() );

        cardDto.user( this.userMapper.toDto(card.getUser()) );

        return cardDto.build();
    }

    @Override
    public CardDto toDtoNotUser(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDtoBuilder cardDto = CardDto.builder();

        cardDto.cardId( card.getCardId() );
        cardDto.cardName( card.getCardName() );
        cardDto.cardNumber( card.getCardNumber() );
        cardDto.userId( card.getUserId() );
        cardDto.updatedAt( card.getUpdatedAt() );
        cardDto.deletedAt( card.getDeletedAt() );

        return cardDto.build();
    }

    @Override
    public CardDto toDtoWithUser(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDtoBuilder cardDto = CardDto.builder();

        cardDto.cardId( card.getCardId() );
        cardDto.cardName( card.getCardName() );
        cardDto.cardNumber( card.getCardNumber() );
        cardDto.userId( card.getUserId() );
        cardDto.updatedAt( card.getUpdatedAt() );
        cardDto.deletedAt( card.getDeletedAt() );

        cardDto.user( userService.get(card.getUserId()).getData() );

        return cardDto.build();
    }

    @Override
    public void updateCardToDto(CardDto dto, Card card) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getCardId() != null ) {
            card.setCardId( dto.getCardId() );
        }
        if ( dto.getCardName() != null ) {
            card.setCardName( dto.getCardName() );
        }
        if ( dto.getCardNumber() != null ) {
            card.setCardNumber( dto.getCardNumber() );
        }
        if ( dto.getUpdatedAt() != null ) {
            card.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            card.setDeletedAt( dto.getDeletedAt() );
        }
    }
}
