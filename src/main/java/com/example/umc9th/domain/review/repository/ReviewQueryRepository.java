package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.querydsl.core.types.Predicate;

import java.util.List;


public interface ReviewQueryRepository {
    List<ReviewResponseDTO.MyReviewResponseDTO> findMyReviews(Long memberId, Predicate predicate); // Predicate: 문서를 받아 boolean값 반환

}
