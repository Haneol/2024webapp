package com.sample.spring.sevice;

import com.sample.spring.api.request.CreateReviewRequest;
import com.sample.spring.dto.ReviewDto;
import com.sample.spring.model.ReviewEntity;
import com.sample.spring.repository.FoodRepository;
import com.sample.spring.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public void createReview(CreateReviewRequest dto) {
        foodRepository.findById(dto.getFoodId()).orElseThrow();

        ReviewEntity review = ReviewEntity.builder()
                .foodId(dto.getFoodId())
                .content(dto.getContent())
                .score(dto.getScore())
                .createdAt(ZonedDateTime.now())
                .build();

        reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId).orElseThrow();
        reviewRepository.delete(review);
    }

    public ReviewDto getFoodReview(Long foodId, Pageable page) {
        Double avgScore = reviewRepository.getAvgScoreByFoodId(foodId);
        Slice<ReviewEntity> reviews = reviewRepository.findSliceByFoodId(foodId, page);

        return ReviewDto.builder()
                .avgScore(avgScore)
                .reviews(reviews.getContent())
                .page(ReviewDto.ReviewDtoPage.builder()
                        .offset(page.getPageNumber() * page.getPageSize())
                        .limit(page.getPageSize())
                        .build())
                .build();
    }
}
