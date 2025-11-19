package com.example.umc9th.domain.store.exception;

import com.example.umc9th.global.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    ADD(HttpStatus.OK,
            "STORE200_1",
            "성공적으로 리뷰를 추가하였습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
