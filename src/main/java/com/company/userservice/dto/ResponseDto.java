package com.company.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    private boolean success;
    private String message;
    /*
     *  0  - success
     * -1 - not found
     * -2 - database error
     * -3 - validation error
     * */
    private int code;
    private T data;
    private List<ErrorDto> errors;
}
