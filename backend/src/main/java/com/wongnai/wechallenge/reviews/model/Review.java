package com.wongnai.wechallenge.reviews.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.lang.NonNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return reviewId == review1.reviewId &&
                review.equals(review1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, review);
    }
}
