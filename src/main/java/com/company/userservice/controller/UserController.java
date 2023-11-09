package com.company.userservice.controller;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.SimpleCRUD;
import com.company.userservice.dto.UserDto;
import com.company.userservice.dto.request.UserRequestDto;
import com.company.userservice.dto.response.UserResponseDto;
import com.company.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import static com.company.userservice.constants.SimpleResponseDto.convertStatusCodeByData;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "user")
public class UserController implements SimpleCRUD<Integer, UserResponseDto, UserRequestDto> {

    private final UserService userService;

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
    public ResponseEntity<ResponseDto<UserResponseDto>> create(@RequestBody UserRequestDto dto) {
        return convertStatusCodeByData(this.userService.create(dto));
    }

    @Override
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDto<UserResponseDto>> get(@PathVariable(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.userService.get(entityId));
    }

    @Override
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseDto<UserResponseDto>> update(@PathVariable(value = "id") Integer entityId,
                                       @RequestBody UserRequestDto dto) {
        return convertStatusCodeByData(this.userService.update(entityId, dto));
    }

    @Override
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseDto<UserResponseDto>> delete(@PathVariable(value = "id") Integer entityId) {
        return convertStatusCodeByData(this.userService.delete(entityId));
    }

    @GetMapping(value = "/get-all")
    public ResponseDto<List<UserResponseDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping(value = "/get-all-page")
    public ResponseDto<Page<UserResponseDto>> getAllUserByPage(@RequestParam Integer page,
                                                       @RequestParam Integer size) {
        return this.userService.getAllUserByPage(page, size);
    }

    @GetMapping(value = "/search-by-user")
    public ResponseDto<List<UserResponseDto>> searchUserByFirstname(@RequestParam String value) {
        return this.userService.searchUserByFirstname(value);
    }

    @GetMapping(value = "/search-by-user-page")
    public ResponseDto<Page<UserResponseDto>> searchAllUserByValue(@RequestParam Integer size,
                                                           @RequestParam Integer page,
                                                           @RequestParam String value) {
        return this.userService.searchAllUserByValue(size, page, value);
    }

    @GetMapping(value = "/get-all-users")
    public ResponseDto<List<UserResponseDto>> getAllUserByValue(@RequestParam(value = "v") String value) {
        return this.userService.getAllUserByValue(value);
    }


    @GetMapping(value = "/search-by-basic")
    public ResponseDto<Page<UserResponseDto>> getAllUserByBasicParams(@RequestParam Map<String, String> params) {
        return this.userService.getAllUserByBasicParams(params);
    }

    @GetMapping(value = "/get-all-user")
    public ResponseDto<List<UserResponseDto>> getAllByDeletedAtIsNotNull(){
        return this.userService.getAllByDeletedAtIsNotNull();
    }


}

