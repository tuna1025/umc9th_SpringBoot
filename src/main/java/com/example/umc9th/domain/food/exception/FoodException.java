package com.example.umc9th.domain.food.exception;

import com.example.umc9th.global.exception.GeneralException;

public class FoodException extends GeneralException {
    public FoodException(FoodErrorCode errorCode) {
        super(errorCode);
    }
}
