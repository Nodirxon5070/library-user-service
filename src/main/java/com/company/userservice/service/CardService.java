package com.company.userservice.service;

import com.company.userservice.dto.CardDto;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.SimpleCRUD;
import com.company.userservice.dto.request.CardRequestDto;
import com.company.userservice.dto.response.CardResponseDto;
import com.company.userservice.modul.Card;
import com.company.userservice.repository.CardRepository;
import com.company.userservice.service.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final UserService userService;
    private final CardMapper cardMapper;

    public ResponseDto<CardResponseDto> create(CardRequestDto dto) {
        if (this.userService.get(dto.getUserId()).getData() == null) {
            return ResponseDto.<CardResponseDto>builder()
                    .code(-1)
                    .message("Card is not found!")
                    .build();
        }
        try {
            Card card = this.cardMapper.toEntity(dto);
            card.setCreatedAt(LocalDateTime.now());
            this.cardRepository.save(card);
            return ResponseDto.<CardResponseDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.cardMapper.toDtoWithUser(card))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<CardResponseDto>builder()
                    .message(String.format("Card while saving error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    public ResponseDto<CardResponseDto> get(Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<CardResponseDto>builder()
                    .message("Card is not found!")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<CardResponseDto>builder()
                .success(true)
                .message("OK")
                .data(this.cardMapper.toDto(optional.get()))
                .build();
    }

    public ResponseDto<CardResponseDto> update(Integer entityId, CardRequestDto dto) {
        try {
            Optional<Card> optional =
                    this.cardRepository
                    .findByCardIdAndDeletedAtIsNull(entityId);
            if (optional.isEmpty()) {
                return ResponseDto.<CardResponseDto>builder()
                        .message("Card is not found!")
                        .code(-1)
                        .build();
            }
            Card card = optional.get();
            card.setUpdatedAt(LocalDateTime.now());
            this.cardMapper.updateCardToDto(dto, card);
            this.cardRepository.save(card);
            return ResponseDto.<CardResponseDto>builder()
                    .success(true)
                    .message("OK")
                    .data(this.cardMapper.toDto(optional.get()))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CardResponseDto>builder()
                    .message(String.format("Card while saving error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    public ResponseDto<CardResponseDto> delete(Integer entityId) {
        Optional<Card> optional = this.cardRepository.findByCardIdAndDeletedAtIsNull(entityId);
        if (optional.isEmpty()) {
            return ResponseDto.<CardResponseDto>builder()
                    .message("Card is not found!")
                    .code(-1)
                    .build();
        }
        Card card = optional.get();
        card.setDeletedAt(LocalDateTime.now());
        this.cardRepository.save(card);
        return ResponseDto.<CardResponseDto>builder()
                .success(true)
                .message("OK")
                .data(this.cardMapper.toDto(card))
                .build();
    }

    public Set<CardResponseDto> getCardFromUserId(Integer userId) {
        return this.cardRepository.findAllByUserIdAndDeletedAtIsNull(userId)
                .stream()
                .map(this.cardMapper::toDtoNotUser)
                .collect(Collectors.toSet());

    }

}
