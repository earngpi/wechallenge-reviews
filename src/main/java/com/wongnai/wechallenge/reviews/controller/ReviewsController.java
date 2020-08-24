package com.wongnai.wechallenge.reviews.controller;

import com.wongnai.wechallenge.reviews.model.Review;
import com.wongnai.wechallenge.reviews.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Set;

@RestController
@RequestMapping("reviews")
public class ReviewsController {

    private final DatasetService datasetService;

    @Autowired
    public ReviewsController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable int id) {
        return datasetService.getReview(id);
    }

    @GetMapping
    public Set<Review> searchReviews(@RequestParam(name = "query") String foodName) {
        return datasetService.searchReviews(foodName);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable int id, @RequestBody Review updatedReview) {
        if (id != updatedReview.getReviewId()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Review IDs in request URL and request body do not match.");
        }

        return datasetService.updateReview(updatedReview);
    }
}
