package com.example.umc9th.domain.store.service.command;

import com.example.umc9th.domain.member.Exception.MemberErrorCode;
import com.example.umc9th.domain.member.Exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.converter.StoreConverter;
import com.example.umc9th.domain.store.dto.request.StoreRequestDTO;
import com.example.umc9th.domain.store.exception.StoreErrorCode;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review createReview(Long storeId, StoreRequestDTO.ReviewDTO request) {

        Review review = StoreConverter.toReview(request,
                memberRepository.findById(request.memberId()).orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND)),
                storeRepository.findById(storeId).orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND)));

        return reviewRepository.save(review);
    }
}
