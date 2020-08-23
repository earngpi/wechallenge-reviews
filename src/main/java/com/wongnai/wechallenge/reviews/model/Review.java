package com.wongnai.wechallenge.reviews.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.lang.NonNull;

@Document(indexName = "reviews")
public class Review {
    @Id
    private int reviewId;
    @NonNull
    private String review;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
