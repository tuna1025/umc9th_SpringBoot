package com.example.umc9th.domain.store.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StoreRequestDTO {

    public record ReviewDTO(
            @NotBlank String title,
            @NotBlank String content,
            @NotNull @Min(0) @Max(5) Float score,
            @NotNull Long memberId
    ){}
}
