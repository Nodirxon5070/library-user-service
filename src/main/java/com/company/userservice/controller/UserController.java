package com.company.userservice.controller;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.SimpleCRUD;
import com.company.userservice.dto.UserDto;
import com.company.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "user")
public record UserController(UserService userService) implements SimpleCRUD<Integer, UserDto> {


    @Override
    @Operation(
            summary = "This is a method that enters user information.",
            description = "THis is a method user description.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            )
    )
    @PostMapping(value = "/create")
    public ResponseDto<UserDto> create(@RequestBody UserDto dto) {
        return this.userService.create(dto);
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseDto<UserDto> get(@PathVariable(value = "id") Integer entityId) {
        return this.userService.get(entityId);
    }

    @Override
    @PutMapping(value = "/update/{id}")
    public ResponseDto<UserDto> update(@PathVariable(value = "id") Integer entityId,
                                       @RequestBody UserDto dto) {
        return this.userService.update(entityId, dto);
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseDto<UserDto> delete(@PathVariable(value = "id") Integer entityId) {
        return this.userService.delete(entityId);
    }

    @GetMapping(value = "/get-all")
    public ResponseDto<List<UserDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping(value = "/get-all-page")
    public ResponseDto<Page<UserDto>> getAllUserByPage(@RequestParam Integer page,
                                                       @RequestParam Integer size) {
        return this.userService.getAllUserByPage(page, size);
    }

    @GetMapping(value = "/search-by-user")
    public ResponseDto<List<UserDto>> searchUserByFirstname(@RequestParam String value) {
        return this.userService.searchUserByFirstname(value);
    }

    @GetMapping(value = "/search-by-user-page")
    public ResponseDto<Page<UserDto>> searchAllUserByValue(@RequestParam Integer size,
                                                           @RequestParam Integer page,
                                                           @RequestParam String value) {
        return this.userService.searchAllUserByValue(size, page, value);
    }

    @GetMapping(value = "/get-all-users")
    public ResponseDto<List<UserDto>> getAllUserByValue(@RequestParam(value = "v") String value) {
        return this.userService.getAllUserByValue(value);
    }


    @GetMapping(value = "/search-by-basic")
    public ResponseDto<Page<UserDto>> getAllUserByBasicParams(@RequestParam Map<String, String> params) {
        return this.userService.getAllUserByBasicParams(params);
    }

    @GetMapping(value = "/get-all-user")
    public ResponseDto<List<UserDto>> getAllByDeletedAtIsNotNull(){
        return this.userService.getAllByDeletedAtIsNotNull();
    }


}

