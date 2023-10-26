package com.company.userservice.dto;

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
public class UserDto {
    private Integer userId;
    @NotBlank(message = "Firstname cannot be null or empty")
    private String firstName;
    @NotBlank(message = "Lastname cannot be null or empty")
    private String lastName;
    @NotBlank(message = "Email cannot be null or empty")
    private String email;
    private String gender;
    @NotBlank(message = "Phone Number cannot be null or empty")
    private String phone;
    private String password;
    private Integer cardId;
    private String birthDate;

    private String username;
    private Boolean enabled;

    private Set<CardDto> cards;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
