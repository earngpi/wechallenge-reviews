package com.wongnai.wechallenge.reviews.controller;

import com.wongnai.wechallenge.reviews.model.Review;
import com.wongnai.wechallenge.reviews.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Review> searchReviews(@RequestParam String query) {
        // TODO:
        return null;
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable int id, @RequestBody Review updatedReview) {
        if (id != updatedReview.getReviewId()) {
            // TODO: throw exceptions
        }
        return datasetService.updateReview(updatedReview);
    }
}
