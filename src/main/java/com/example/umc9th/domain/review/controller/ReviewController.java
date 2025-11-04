package com.example.umc9th.domain.review.controller;


import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPaylode.ApiResponse;
import com.example.umc9th.global.exception.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("reviews/my")
    @Operation(summary = "내 리뷰 조회", description = "내가 작성한 리뷰를 가게별, 별점별로 필터링하여 조회")
    public ApiResponse<List<ReviewResponseDTO.MyReviewResponseDTO>> getMyReviews(
            @Parameter(description = "조회할 회원 ID, 나중엔 jwt 사용")
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer star,
            @Parameter(description = "필터 타입: store, star, both, none")
            @RequestParam(defaultValue = "none") String filterType
    ) {
        List<ReviewResponseDTO.MyReviewResponseDTO> reviews = reviewQueryService.getMyReviews(memberId, storeName, star, filterType);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviews);
    }
}

