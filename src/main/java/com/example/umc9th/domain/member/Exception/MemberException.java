package com.example.umc9th.domain.member.Exception;

import com.example.umc9th.global.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(MemberErrorCode errorCode) {
        super(errorCode);
    }
}
