package com.company.userservice.controller;

import com.company.userservice.dto.CardDto;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.SimpleCRUD;
import com.company.userservice.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "card")
@RequiredArgsConstructor
public class CardController implements SimpleCRUD<Integer, CardDto> {

    private final CardService cardService;

    @Override
    @PostMapping(value = "/create")
    public ResponseDto<CardDto> create(@RequestBody CardDto dto) {
        return this.cardService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    public ResponseDto<CardDto> get(@RequestParam(value = "id") Integer entityId) {
        return this.cardService.get(entityId);
    }

    @Override
    @PutMapping(value = "/update/{id}")
    public ResponseDto<CardDto> update(@PathVariable(value = "id") Integer entityId,
                                       @RequestBody CardDto dto) {
        return this.cardService.update(entityId, dto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<CardDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.cardService.delete(entityId);
    }

}
