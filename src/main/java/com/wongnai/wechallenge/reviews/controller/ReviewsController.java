package com.wongnai.wechallenge.reviews.controller;

import com.wongnai.wechallenge.reviews.model.Review;
import com.wongnai.wechallenge.reviews.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("reviews")
public class ReviewsController {

    @Autowired
    DatasetService datasetService;

    @GetMapping("/{id}")
    public Review getReview(@PathVariable int id) {
        return datasetService.getReview(id);
    }

    @GetMapping()
    public Set<Review> searchReviews(@RequestParam(name = "query") String foodName) {
        return datasetService.searchReviews(foodName);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable int id, @RequestBody Review updatedReview) {
        if (id != updatedReview.getReviewId()) {
            // TODO: throw exceptions
        }
        return datasetService.updateReview(updatedReview);
    }
}
