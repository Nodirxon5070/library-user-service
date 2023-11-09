package com.company.userservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String phone;
    private String password;
    private Integer cardId;
    private String birthDate;

    private String username;
    private Boolean enabled;

    private Set<CardResponseDto> cards;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
