package com.example.umc9th.global.validator;

import com.example.umc9th.global.annotation.CheckPage;
import com.example.umc9th.global.exception.GeneralErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(GeneralErrorCode.BAD_REQUEST_400.getMessage()) // 또는 커스텀 에러 메시지
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}