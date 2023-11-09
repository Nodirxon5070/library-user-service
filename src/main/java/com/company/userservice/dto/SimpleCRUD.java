package com.company.userservice.dto;

import org.springframework.http.ResponseEntity;

public interface SimpleCRUD<K, RS, RQ> {
    ResponseEntity<ResponseDto<RS>> create(RQ dto);

    ResponseEntity<ResponseDto<RS>> get(K entityId);

    ResponseEntity<ResponseDto<RS>> update(K entityId, RQ dto);

    ResponseEntity<ResponseDto<RS>> delete(K entityId);
}

