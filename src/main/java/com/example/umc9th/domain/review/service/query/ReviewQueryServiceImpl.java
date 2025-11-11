package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewResponseDTO.MyReviewResponseDTO> getMyReviews(
            Long memberId,
            String storeName,
            Integer star,
            String filterType
    ) {
        QReview review = QReview.review;
        QStore store = QStore.store;
        BooleanBuilder builder = new BooleanBuilder();

        if ("store".equalsIgnoreCase(filterType) && storeName != null && !storeName.isEmpty()) {
            builder.and(store.name.contains(storeName));
        } else if ("star".equalsIgnoreCase(filterType) && star != null) {
            float min = star.floatValue();
            float max = star + 1f;
            builder.and(review.star.goe(min)).and(review.star.lt(max));
        } else if ("both".equalsIgnoreCase(filterType)) {
            if (storeName != null && !storeName.isEmpty()) {
                builder.and(store.name.contains(storeName));
            }
            if (star != null) {
                float min = star.floatValue();
                float max = star + 1f;
                builder.and(review.star.goe(min)).and(review.star.lt(max));
            }
        }

        return reviewRepository.findMyReviews(memberId, builder);
    }
}
