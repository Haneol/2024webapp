package com.sample.spring.repository;

import com.sample.spring.model.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReviewRepositoryCustom {
    Slice<ReviewEntity> findSliceByFoodId(Long foodId, Pageable page);
    Double getAvgScoreByFoodId(Long foodId);
}
