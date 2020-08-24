package com.wongnai.wechallenge.reviews.controller;

import com.wongnai.wechallenge.reviews.model.Review;
import com.wongnai.wechallenge.reviews.service.DatasetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.Mockito.*;

public class ReviewControllerTest {

    static private ReviewsController reviewController;
    static private DatasetService datasetServiceMock;

    static private Review review = new Review();

    static final private int REVIEW_ID_1 = 1;
    static final private int REVIEW_ID_2 = 2;
    static final private String FOOD_NAME = "food name";

    @BeforeEach
    public void init() {
        datasetServiceMock = mock(DatasetService.class);
        doReturn(null).when(datasetServiceMock).getReview(anyInt());
        doReturn(null).when(datasetServiceMock).searchReviews(anyString());
        doReturn(null).when(datasetServiceMock).updateReview(any(Review.class));

        reviewController = new ReviewsController(datasetServiceMock);
    }

    @Test
    public void testGetReview_datasetServiceMethodInvokedOnce() {
        reviewController.getReview(REVIEW_ID_1);
        verify(datasetServiceMock, times(1)).getReview(REVIEW_ID_1);
    }

    @Test
    public void testSearchReviews_datasetServiceMethodInvokedOnce() {
        reviewController.searchReviews(FOOD_NAME);
        verify(datasetServiceMock, times(1)).searchReviews(FOOD_NAME);
    }

    @Test
    void testUpdateReview_idsInRequestAndBodyMatch_datasetServiceMethodInvokedOnce() {
        review.setReviewId(REVIEW_ID_1);
        reviewController.updateReview(REVIEW_ID_1, review);
        verify(datasetServiceMock, times(1)).updateReview(review);
    }

    @Test
    void testUpdateReview_idsInRequestAndBodyDiff_exceptionThrown_datasetServiceMethodNotInvoked() {
        review.setReviewId(REVIEW_ID_2);
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            reviewController.updateReview(REVIEW_ID_1, review);
        });
        verify(datasetServiceMock, times(0)).updateReview(any(Review.class));
    }
}
