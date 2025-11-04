package com.example.umc9th.domain.review.repository;


import com.example.umc9th.domain.review.dto.response.ReviewResponseDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

    private final EntityManager em;

    @Override
    public List<ReviewResponseDTO.MyReviewResponseDTO> findMyReviews(Long memberId, Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QStore store = QStore.store;

        return queryFactory
                .select(Projections.constructor(
                        ReviewResponseDTO.MyReviewResponseDTO.class,
                        review.id,
                        store.name,
                        review.title,
                        review.content,
                        review.star,
                        review.createdAt
                ))
                .from(review)
                .leftJoin(review.store, store)
                .where(
                        review.member.id.eq(memberId),
                        predicate
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
