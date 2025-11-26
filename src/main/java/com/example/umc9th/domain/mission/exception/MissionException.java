package com.example.umc9th.domain.mission.exception;

import com.example.umc9th.global.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(MissionErrorCode errorCode) {
        super(errorCode);
    }
}
