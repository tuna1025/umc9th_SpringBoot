package com.example.umc9th.domain.mission.exception;

import com.example.umc9th.global.code.BaseErrorCode;
import com.example.umc9th.global.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    ADD(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 추가하였습니다."),
    COMPLETE(HttpStatus.OK,"MISSION200_2","미션 완료"),
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
