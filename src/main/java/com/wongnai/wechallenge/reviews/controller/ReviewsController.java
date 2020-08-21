package com.wongnai.wechallenge.reviews.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reviews")
public class ReviewsController {

    @GetMapping("/{id}")
    public void getReview(@PathVariable int id) {
        // TODO:
    }

    @GetMapping()
    public void searchReviews(@RequestParam String query) {
        // TODO:
    }

    @PutMapping("/{id}")
    public void editReview(@PathVariable int id) {
        // TODO:
    }
}
