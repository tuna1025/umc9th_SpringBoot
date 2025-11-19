package com.example.umc9th.domain.member.dto.request;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            String address,
            @NotNull
            String specAddress,
            @ExistFoods
            List<Long> preferCategory
    ){}
}
