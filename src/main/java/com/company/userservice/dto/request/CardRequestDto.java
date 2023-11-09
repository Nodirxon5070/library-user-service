package com.company.userservice.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardRequestDto {
    private String cardName;
    private Long cardNumber;
    private Integer userId;


    private UserRequestDto user;

}
