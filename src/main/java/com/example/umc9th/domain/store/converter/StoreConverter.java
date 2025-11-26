package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.dto.request.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.response.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static Review toReview(StoreRequestDTO.ReviewDTO request, Member member, Store store){
        return Review.builder()
                .title(request.title())
                .content(request.content())
                .star(request.score())
                .member(member)
                .store(store)
                .build();
    }

    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return new StoreResponseDTO.CreateReviewResultDTO(review.getId(), LocalDateTime.now());
    }
}
