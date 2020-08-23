package com.wongnai.wechallenge.reviews.service;

import com.wongnai.wechallenge.reviews.repository.ReviewRepository;
import com.wongnai.wechallenge.reviews.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DatasetService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public DatasetService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review getReview(int id) {
        return reviewRepository.findReviewByReviewId(id);
    }

    public Set<String> searchReviews(String query) {
        return null;
    }

    public Review updateReview(Review updatedReview) {
        if (reviewRepository.findReviewByReviewId(updatedReview.getReviewId()) == null) {
            // TODO: throws exception
            return null;
        }

        return reviewRepository.save(updatedReview);
    }
}
