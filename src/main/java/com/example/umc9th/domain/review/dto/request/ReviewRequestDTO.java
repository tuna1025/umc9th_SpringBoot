package com.example.umc9th.domain.review.dto.request;

import lombok.Builder;

public class ReviewRequestDTO {

    @Builder
    public record MyReviewRequestDTO(
            String storeName,
            Integer star,
            String filterType // "store", "star", "both", "none"
    ) {}
}
