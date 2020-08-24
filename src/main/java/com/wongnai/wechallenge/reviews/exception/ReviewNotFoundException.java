package com.wongnai.wechallenge.reviews.exception;

/**
 * This exception is thrown
 * when NO reviews, matching reviewId specified in client request, has been found
 */
public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(String errMsg) {
        super(errMsg);
    }

}
