package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.request.StoreRequestDTO;

public interface StoreCommandService {
    Review createReview(Long storeId, StoreRequestDTO.ReviewDTO request);
}
