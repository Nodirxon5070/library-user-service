package com.company.userservice.service;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.SimpleCRUD;
import com.company.userservice.dto.UserDto;
import com.company.userservice.dto.request.UserRequestDto;
import com.company.userservice.dto.response.UserResponseDto;
import com.company.userservice.modul.User;
import com.company.userservice.repository.UserRepository;
import com.company.userservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    protected final UserMapper userMapper;
    public ResponseDto<UserResponseDto> create(UserRequestDto dto) {
        try {
            return Optional.ofNullable(this.userMapper.toEntity(dto))
                    .map(user -> {
                        user.setCreatedAt(LocalDateTime.now());
                        return ResponseDto.<UserResponseDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.userMapper.toDto(this.userRepository.save(user)))
                                .build();
                    }).orElse(null);
        } catch (Exception e) {
            return ResponseDto.<UserResponseDto>builder()
                    .message(String.format("User while saving error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    public ResponseDto<UserResponseDto> get(Integer entityId) {
        return this.userRepository.findByUserIdAndDeletedAtIsNull(entityId)
                .map(user -> ResponseDto.<UserResponseDto>builder()
                        .success(true)
                        .message("OK")
                        .data(this.userMapper.toDtoWithCard(user))
                        .build())
                .orElse(ResponseDto.<UserResponseDto>builder()
                        .message(String.format("User with id: %s is not found!", entityId))
                        .code(-1)
                        .build());
    }

    public ResponseDto<UserResponseDto> update(Integer entityId, UserRequestDto dto) {
        try {
            return this.userRepository.findByUserIdAndDeletedAtIsNull(entityId)
                    .map(user -> {
                        user.setUpdatedAt(LocalDateTime.now());
                        return ResponseDto.<UserResponseDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.userMapper.toDto(
                                        this.userRepository.save(
                                                this.userMapper.updateUserFromDto(dto, user)
                                        )
                                ))
                                .build();
                    })
                    .orElse(ResponseDto.<UserResponseDto>builder()
                            .message("User is not found!")
                            .code(-1)
                            .build());
        } catch (Exception e) {
            return ResponseDto.<UserResponseDto>builder()
                    .message(String.format("User while updating error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    public ResponseDto<UserResponseDto> delete(Integer entityId) {
        try {
            return this.userRepository.findByUserIdAndDeletedAtIsNull(entityId)
                    .map(user -> {
                        user.setDeletedAt(LocalDateTime.now());
                        return ResponseDto.<UserResponseDto>builder()
                                .success(true)
                                .message("OK")
                                .data(this.userMapper.toDto(
                                        this.userRepository.save(user)
                                ))
                                .build();
                    })
                    .orElse(ResponseDto.<UserResponseDto>builder()
                            .message("User is not found!")
                            .code(-1)
                            .build());
        } catch (Exception e) {
            return ResponseDto.<UserResponseDto>builder()
                    .message(String.format("User deleting saving error %s", e.getMessage()))
                    .code(-2)
                    .build();
        }
    }

    public ResponseDto<List<UserResponseDto>> getAllUsers() {
        List<User> userList = this.userRepository.findAllByDeletedAtIsNull();
        if (userList.isEmpty()) {
            return ResponseDto.<List<UserResponseDto>>builder()
                    .message("Users are not found!")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<List<UserResponseDto>>builder()
                .message("OK")
                .data(userList.stream().map(this.userMapper::toDto).toList())
                .build();
    }

    public ResponseDto<Page<UserResponseDto>> getAllUserByPage(Integer page, Integer size) {
        Page<User> userPage = this.userRepository.findAllByDeletedAtIsNull(PageRequest.of(page, size));
        if (userPage.isEmpty()) {
            return ResponseDto.<Page<UserResponseDto>>builder()
                    .message("Users are not found!")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<Page<UserResponseDto>>builder()
                .success(true)
                .message("OK")
                .data(userPage.map(this.userMapper::toDto))
                .build();
    }

    public ResponseDto<List<UserResponseDto>> searchUserByFirstname(String value) {
        List<User> userList = this.userRepository.searchUserByFirstname(value);
        if (userList.isEmpty()) {
            return ResponseDto.<List<UserResponseDto>>builder()
                    .message("Users are not found!")
                    .code(-1)
                    .build();
        }
        return ResponseDto.<List<UserResponseDto>>builder()
                .success(true)
                .message("OK")
                .data(userList.stream().map(this.userMapper::toDto).toList())
                .build();
    }


    public ResponseDto<Page<UserResponseDto>> searchAllUserByValue(Integer size, Integer page, String value) {
        return Optional.ofNullable(this.userRepository.searchAllUserByValue(PageRequest.of(page, size), value))
                .map(users -> ResponseDto.<Page<UserResponseDto>>builder()
                        .success(true)
                        .message("OK")
                        .data(users.map(this.userMapper::toDto))
                        .build())
                .orElse(ResponseDto.<Page<UserResponseDto>>builder()
                        .message("Users are not found!")
                        .code(-1)
                        .build());
    }

    public ResponseDto<List<UserResponseDto>> getAllUserByValue(String value) {
        return Optional.ofNullable(this.userRepository.findAllUserByNameValue(value))
                .map(users -> ResponseDto.<List<UserResponseDto>>builder()
                        .success(true)
                        .message("OK")
                        .data(users.stream().map(this.userMapper::toDto).toList())
                        .build())
                .orElse(ResponseDto.<List<UserResponseDto>>builder()
                        .message("Users are not found!")
                        .code(-1)
                        .build());
    }

    public ResponseDto<Page<UserResponseDto>> getAllUserByBasicParams(Map<String, String> params) {
        int page = 0, size = 10;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt(params.get("size"));
        }

        Page<User> users = this.userRepository.findAllUserByParams(
                params.get("id") == null ? null : Integer.parseInt(params.get("id")),
                params.get("f"), params.get("l"),
                params.get("e"), params.get("p"),
                params.get("bd"), params.get("ph"),
                PageRequest.of(page, size)
        );

        return ResponseDto.<Page<UserResponseDto>>builder()
                .success(true)
                .message("OK")
                .data(users.map(this.userMapper::toDto))
                .build();

    }

    public ResponseDto<List<UserResponseDto>> getAllByDeletedAtIsNotNull() {
        List<User> userList = this.userRepository.findAllByDeletedAtIsNotNull();
        if (userList.isEmpty()) {
            return ResponseDto.<List<UserResponseDto>>builder()
                    .success(true)
                    .message("User are not found!")
                    .build();
        }
        return ResponseDto.<List<UserResponseDto>>builder()
                .success(true)
                .message("OK")
                .data(userList.stream().map(this.userMapper::toDto).toList())
                .build();

    }

}