package com.wongnai.wechallenge.reviews.service;

import com.wongnai.wechallenge.reviews.exception.ReviewNotFoundException;
import com.wongnai.wechallenge.reviews.model.Food;
import com.wongnai.wechallenge.reviews.repository.FoodRepository;
import com.wongnai.wechallenge.reviews.repository.ReviewRepository;
import com.wongnai.wechallenge.reviews.model.Review;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DatasetService {

    private static final String NO_REVIEWS_MATCHING_ID_MSG = "No reviews matching specified review ID";

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FoodRepository foodRepository;

    public Review getReview(int id) {
        Review review = reviewRepository.findReviewByReviewId(id);
        if (review == null) {
            throw new ReviewNotFoundException(NO_REVIEWS_MATCHING_ID_MSG);
        }

        return review;
    }

    public Set<Review> searchReviews(String foodName) {
        String foodNameWithTags = "<keyword>" + foodName + "</keyword>";

        Food food = foodRepository.findFoodByFoodNameEquals(foodName);
        if (food == null || !food.getFoodName().equals(foodName)) {
            return new HashSet<>();
        }

        Set<Review> reviewSet = reviewRepository.findReviewsByFoodName(food.getFoodName());

        for (Review review : reviewSet) {
            review.setReview(StringUtils.replaceIgnoreCase(review.getReview(), foodName, foodNameWithTags));
        }

        return reviewSet;
    }

    public Review updateReview(Review updatedReview) {
        if (reviewRepository.findReviewByReviewId(updatedReview.getReviewId()) == null) {
            throw new ReviewNotFoundException(NO_REVIEWS_MATCHING_ID_MSG);
        }

        return reviewRepository.save(updatedReview);
    }
}
