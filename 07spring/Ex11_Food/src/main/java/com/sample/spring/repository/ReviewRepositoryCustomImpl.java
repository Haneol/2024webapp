package com.sample.spring.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sample.spring.model.QReviewEntity;
import com.sample.spring.model.ReviewEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<ReviewEntity> findSliceByFoodId(Long foodId, Pageable page) {
        List<ReviewEntity> reviews = queryFactory.select(QReviewEntity.reviewEntity)
                .from(QReviewEntity.reviewEntity)
                .where(QReviewEntity.reviewEntity.foodId.eq(foodId))
                .offset((long) page.getPageNumber() * page.getPageSize()) // 0 page * 10 pagesize
                .limit(page.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(
                reviews.stream().limit(page.getPageSize()).toList(),
                page,
                reviews.size() > page.getPageSize());
    }

    @Override
    public Double getAvgScoreByFoodId(Long foodId) {
        Double avgScore = queryFactory.select(QReviewEntity.reviewEntity.score.avg())
                .from(QReviewEntity.reviewEntity)
                .where(QReviewEntity.reviewEntity.foodId.eq(foodId))
                .fetchOne();

        return avgScore;
    }
}
