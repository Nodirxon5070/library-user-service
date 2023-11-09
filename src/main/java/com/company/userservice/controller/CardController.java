package com.company.userservice.controller;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.SimpleCRUD;
import com.company.userservice.dto.request.CardRequestDto;
import com.company.userservice.dto.response.CardResponseDto;
import com.company.userservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.company.userservice.constants.SimpleResponseDto.convertStatusCodeByData;



@RestController
@RequestMapping(value = "card")
@RequiredArgsConstructor
public class CardController implements SimpleCRUD<Integer, CardResponseDto, CardRequestDto> {

    private final CardService cardService;

    @Override
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto<CardResponseDto>> create(@RequestBody CardRequestDto dto) {
        return convertStatusCodeByData(this.cardService.create(dto));
    }

    @Override
    @GetMapping(value = "/get")
    public ResponseEntity<ResponseDto<CardResponseDto>> get(@RequestParam(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.cardService.get(entityId));
    }

    @Override
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseDto<CardResponseDto>> update(@PathVariable(value = "id") Integer entityId,
                                       @RequestBody CardRequestDto dto) {
        return convertStatusCodeByData(this.cardService.update(entityId, dto));
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseDto<CardResponseDto>> delete(@PathVariable(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.cardService.delete(entityId));
    }

}
