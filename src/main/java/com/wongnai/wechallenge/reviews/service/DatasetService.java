package com.wongnai.wechallenge.reviews.service;

import com.wongnai.wechallenge.reviews.model.Food;
import com.wongnai.wechallenge.reviews.repository.FoodRepository;
import com.wongnai.wechallenge.reviews.repository.ReviewRepository;
import com.wongnai.wechallenge.reviews.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DatasetService {

    private final ReviewRepository reviewRepository;

    private final FoodRepository foodRepository;

    @Autowired
    public DatasetService(ReviewRepository reviewRepository, FoodRepository foodRepository) {
        this.reviewRepository = reviewRepository;
        this.foodRepository = foodRepository;
    }

    public Review getReview(int id) {
        Review review = reviewRepository.findReviewByReviewId(id);
        if (review == null) {
            // TODO: throw exception - same one as updateReview
        }

        return review;
    }

    public Set<Review> searchReviews(String foodName) {
        Food food = foodRepository.findFoodByFoodNameEquals(foodName);
        if (food == null || !food.getFoodName().equals(foodName)) {
            return new HashSet<>();
        }

        return reviewRepository.findReviewsByFoodName(food.getFoodName());
    }

    public Review updateReview(Review updatedReview) {
        if (reviewRepository.findReviewByReviewId(updatedReview.getReviewId()) == null) {
            // TODO: throw exception - same one as getReview
        }

        return reviewRepository.save(updatedReview);
    }
}
