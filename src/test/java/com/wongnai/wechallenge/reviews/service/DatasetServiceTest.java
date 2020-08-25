package com.wongnai.wechallenge.reviews.service;

import com.wongnai.wechallenge.reviews.exception.ReviewNotFoundException;
import com.wongnai.wechallenge.reviews.model.Food;
import com.wongnai.wechallenge.reviews.model.Review;
import com.wongnai.wechallenge.reviews.repository.FoodRepository;
import com.wongnai.wechallenge.reviews.repository.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class DatasetServiceTest {

    static private DatasetService datasetService;
    static private ReviewRepository reviewRepositoryMock;
    static private FoodRepository foodRepositoryMock;

    static private Review review = new Review();
    static private Review updatedReview = new Review();
    static private Food food = new Food();

    static final private String REVIEW_TEXT = "review text with food name 1";
    static final private String UPDATED_REVIEW_TEXT = "updated review text with food name 1";
    static final private int REVIEW_ID_1 = 1;
    static final private String FOOD_NAME_1 = "food name 1";
    static final private String FOOD_NAME_1_2 = "food name 1 2";
    static final private Set<Review> EMPTY_SET = new HashSet<>();

    @BeforeEach
    public void init() {
        reviewRepositoryMock = mock(ReviewRepository.class);
        foodRepositoryMock = mock(FoodRepository.class);

        review.setReview(REVIEW_TEXT);
        review.setReviewId(REVIEW_ID_1);
        updatedReview.setReview(UPDATED_REVIEW_TEXT);
        updatedReview.setReviewId(REVIEW_ID_1);
        food.setFoodName(FOOD_NAME_1);

        datasetService = new DatasetService(reviewRepositoryMock, foodRepositoryMock);
    }

 // ---------------------------------------------------------------------- testGetReview ----------------------------------------------------------------------

    @Test
    public void testGetReview_foundReviewInDb() {
        doReturn(review).when(reviewRepositoryMock).findReviewByReviewId(anyInt());
        datasetService.getReview(REVIEW_ID_1);
        verify(reviewRepositoryMock, times(1)).findReviewByReviewId(REVIEW_ID_1);
    }

    @Test
    public void testGetReview_NoReviewInDb_reviewNotFoundExThrown() {
        doReturn(null).when(reviewRepositoryMock).findReviewByReviewId(anyInt());
        Assertions.assertThrows(ReviewNotFoundException.class, () -> {
            datasetService.getReview(REVIEW_ID_1);
        });
        verify(reviewRepositoryMock, times(1)).findReviewByReviewId(REVIEW_ID_1);
    }

// -------------------------------------------------------------------- testSearchReview --------------------------------------------------------------------

    @Test
    public void testSearchReviews_foodNameNotInDb_EmptySetReturned() {
        doReturn(null).when(foodRepositoryMock).findFoodByFoodNameEquals(anyString());
        Set<Review> returnedReviewSet = datasetService.searchReviews(FOOD_NAME_1);
        assertEquals(EMPTY_SET, returnedReviewSet);
        verify(foodRepositoryMock, times(1)).findFoodByFoodNameEquals(FOOD_NAME_1);
        verify(reviewRepositoryMock, times(0)).findReviewsByFoodName(anyString());
    }

    @Test
    public void testSearchReviews_foodNameIsSubstringOfOthersInDb_EmptySetReturned() {
        food.setFoodName(FOOD_NAME_1_2);
        doReturn(food).when(foodRepositoryMock).findFoodByFoodNameEquals(anyString());
        Set<Review> returnedReviewSet = datasetService.searchReviews(FOOD_NAME_1);
        assertEquals(EMPTY_SET, returnedReviewSet);
        verify(foodRepositoryMock, times(1)).findFoodByFoodNameEquals(FOOD_NAME_1);
        verify(reviewRepositoryMock, times(0)).findReviewsByFoodName(anyString());
    }

    @Test
    public void testSearchReviews_noReviewsInDbContainFood_EmptySetReturned() {
        doReturn(food).when(foodRepositoryMock).findFoodByFoodNameEquals(anyString());
        doReturn(EMPTY_SET).when(reviewRepositoryMock).findReviewsByFoodName(anyString());
        Set<Review> returnedReviewSet = datasetService.searchReviews(FOOD_NAME_1);
        assertEquals(EMPTY_SET, returnedReviewSet);
        verify(foodRepositoryMock, times(1)).findFoodByFoodNameEquals(FOOD_NAME_1);
        verify(reviewRepositoryMock, times(1)).findReviewsByFoodName(FOOD_NAME_1);
    }

    @Test
    public void testSearchReviews_oneReviewInDbContainFood_setOfOneReviewReturned() {
        Set<Review> reviewSetFromDb = new HashSet<>();
        reviewSetFromDb.add(review);

        Review reviewWithKeywordTags = new Review();
        reviewWithKeywordTags.setReview("review text with <keyword>food name 1</keyword>");
        reviewWithKeywordTags.setReviewId(REVIEW_ID_1);
        Set<Review> expectedReviewSet = new HashSet<>();
        expectedReviewSet.add(reviewWithKeywordTags);

        doReturn(food).when(foodRepositoryMock).findFoodByFoodNameEquals(anyString());
        doReturn(reviewSetFromDb).when(reviewRepositoryMock).findReviewsByFoodName(anyString());
        Set<Review> returnedReviewSet = datasetService.searchReviews(FOOD_NAME_1);

        assertEquals(expectedReviewSet, returnedReviewSet);
        verify(foodRepositoryMock, times(1)).findFoodByFoodNameEquals(FOOD_NAME_1);
        verify(reviewRepositoryMock, times(1)).findReviewsByFoodName(FOOD_NAME_1);
    }

// ------------------------------------------------------------------- testUpdateReview -------------------------------------------------------------------

    @Test
    public void testUpdateReview_foundReviewInDb_returnedReviewIsSameAsInput() {
        doReturn(updatedReview).when(reviewRepositoryMock).findReviewByReviewId(anyInt());
        doReturn(updatedReview).when(reviewRepositoryMock).save(any(Review.class));
        datasetService.updateReview(updatedReview);
        verify(reviewRepositoryMock, times(1)).findReviewByReviewId(REVIEW_ID_1);
        verify(reviewRepositoryMock, times(1)).save(updatedReview);
    }

    @Test
    public void testUpdateReview_NoReviewInDb_reviewNotFoundExThrown() {
        doReturn(null).when(reviewRepositoryMock).findReviewByReviewId(anyInt());
        doReturn(null).when(reviewRepositoryMock).save(any(Review.class));
        Assertions.assertThrows(ReviewNotFoundException.class, () -> {
            datasetService.updateReview(updatedReview);
        });
        verify(reviewRepositoryMock, times(1)).findReviewByReviewId(REVIEW_ID_1);
        verify(reviewRepositoryMock, times(0)).save(updatedReview);
    }
}
