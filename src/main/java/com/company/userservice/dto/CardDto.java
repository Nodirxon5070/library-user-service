package com.company.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {
    private Integer cardId;
    private String cardName;
    private Long cardNumber;
    private Integer userId;

    private UserDto user;

    private LocalDateTime created_at;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
