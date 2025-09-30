package com.example.umc9th.domain.test;

import com.example.umc9th.global.code.BaseErrorCode;
import com.example.umc9th.global.exception.GeneralErrorCode;
import com.example.umc9th.global.exception.GeneralException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "테스트 API")
public class TestController {

    @GetMapping("/test")
    @Operation(summary = "테스트",description = "테스트")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/exception")
    @Operation(summary = "예외 테스트용 api")
    public String exception() {
        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST_400;
        throw new GeneralException(code);
    }


}
