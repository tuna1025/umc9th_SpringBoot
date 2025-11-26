package com.example.umc9th.domain.store.dto.response;

import java.time.LocalDateTime;

public class StoreResponseDTO {

    public record CreateReviewResultDTO(
            Long reviewId,
            LocalDateTime createdAt
    ){}

}
