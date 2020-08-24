package com.wongnai.wechallenge.reviews.controller;

import com.wongnai.wechallenge.reviews.model.Review;
import com.wongnai.wechallenge.reviews.service.DatasetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.Mockito.*;

public class ReviewControllerTest {

    static ReviewsController reviewControllerSpy;
    static DatasetService datasetServiceMock;

    static int testId = 130;
    static String testFoodName = "food name";
    static Review testReview = new Review();

    @BeforeAll
    public static void init() {
        datasetServiceMock = Mockito.mock(DatasetService.class);
        doReturn(null).when(datasetServiceMock).getReview(anyInt());
        doReturn(null).when(datasetServiceMock).searchReviews(anyString());
        doReturn(null).when(datasetServiceMock).updateReview(any(Review.class));

        reviewControllerSpy = Mockito.spy(ReviewsController.class);
        ReflectionTestUtils.setField(reviewControllerSpy, "datasetService", datasetServiceMock);
    }

    @Test
    public void testGetReview_datasetServiceMethodInvokedOnce() {
        reviewControllerSpy.getReview(testId);
        verify(datasetServiceMock, times(1)).getReview(testId);
    }

    @Test
    public void testSearchReviews_datasetServiceMethodInvokedOnce() {
        reviewControllerSpy.searchReviews(testFoodName);
        verify(datasetServiceMock, times(1)).searchReviews(testFoodName);
    }

    @Test
    void testUpdateReview_idsInRequestAndBodyMatch_datasetServiceMethodInvokedOnce() {
        testReview.setReviewId(testId);
        reviewControllerSpy.updateReview(testId, testReview);
        verify(datasetServiceMock, times(1)).updateReview(testReview);
    }

    @Test
    void testUpdateReview_idsRequestAndBodyDiff_exceptionThrown_datasetServiceMethodNotInvoked() {
        testReview.setReviewId(testId + 1);
        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            reviewControllerSpy.updateReview(testId, testReview);
        });
        verify(datasetServiceMock, times(0)).updateReview(testReview);
    }
}
