package com.example.umc9th.domain.member.Exception;

import com.example.umc9th.global.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND("MEMBER_400","해당 회원을 찾을 수 없습니다", HttpStatus.NOT_FOUND),;

    private String code;
    private String message;
    private HttpStatus status;


}
