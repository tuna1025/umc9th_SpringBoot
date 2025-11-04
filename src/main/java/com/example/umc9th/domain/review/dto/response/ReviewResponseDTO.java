package com.example.umc9th.domain.review.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Builder
    public record MyReviewResponseDTO(
            Long reviewId,
            String storeName,
            String title,
            String content,
            Float star,
            LocalDateTime createdAt
    ) {}
}
