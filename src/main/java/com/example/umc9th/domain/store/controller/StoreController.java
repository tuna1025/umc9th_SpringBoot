package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.request.StoreRequestDTO;
import com.example.umc9th.domain.store.dto.response.StoreResponseDTO;
import com.example.umc9th.domain.store.exception.StoreSuccessCode;
import com.example.umc9th.domain.store.service.command.StoreCommandService;
import com.example.umc9th.global.apiPaylode.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid StoreRequestDTO.ReviewDTO request
    ){
        Review review = storeCommandService.createReview(storeId, request);
        return ApiResponse.onSuccess(StoreSuccessCode.ADD,StoreConverter.toCreateReviewResultDTO(review));
    }
}
