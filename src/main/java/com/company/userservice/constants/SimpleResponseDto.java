package com.company.userservice.constants;

import com.company.userservice.dto.ResponseDto;
import lombok.Builder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Builder
public class SimpleResponseDto {
    public static  <T> ResponseEntity<ResponseDto<T>> convertStatusCodeByData(ResponseDto<T> dto){
        switch (dto.getCode()){
            case 0 ->{
                return new ResponseEntity<>(dto, HttpStatusCode.valueOf(200));
            }
            case -1 ->{
                return new ResponseEntity<>(dto, HttpStatusCode.valueOf(404));
            }
            case -2,-3 ->{
                return new ResponseEntity<>(dto, HttpStatusCode.valueOf(400));
            }
            default -> {
                return null;
            }
        }
    }
}
