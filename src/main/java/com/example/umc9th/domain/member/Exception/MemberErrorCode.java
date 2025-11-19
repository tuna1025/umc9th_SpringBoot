package com.example.umc9th.domain.member.Exception;

import com.example.umc9th.global.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾지 못했습니다."),
    ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST,"MEMBER400_1","이미 등록된 미션입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
