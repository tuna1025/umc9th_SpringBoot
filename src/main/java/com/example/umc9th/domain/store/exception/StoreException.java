package com.example.umc9th.domain.store.exception;

import com.example.umc9th.global.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(StoreErrorCode errorCode) {
        super(errorCode);
    }
}
