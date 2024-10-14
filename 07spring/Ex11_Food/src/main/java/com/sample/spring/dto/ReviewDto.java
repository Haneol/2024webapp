package com.sample.spring.dto;

import com.sample.spring.model.ReviewEntity;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Double avgScore;
    private List<ReviewEntity> reviews;
    private ReviewDtoPage page;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewDtoPage {
        private Integer offset;
        private Integer limit;

    }
}
