package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;

import java.util.List;

public interface ReviewQueryService {
    List<ReviewResponseDTO.MyReviewResponseDTO> getMyReviews(
            Long memberId,
            String storeName,
            Integer star,
            String filterType
    );
}
