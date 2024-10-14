package com.sample.spring.api;

import com.sample.spring.api.request.CreateReviewRequest;
import com.sample.spring.dto.ReviewDto;
import com.sample.spring.sevice.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;

    @GetMapping("/review/{foodId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long foodId, Pageable page) {
        return ResponseEntity.ok(reviewService.getFoodReview(foodId, page));
    }

    @PostMapping("/review")
    public ResponseEntity<String> createReview(@RequestBody CreateReviewRequest review) {
        reviewService.createReview(review);

        return ResponseEntity.status(HttpStatus.CREATED).body("Review created");
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
