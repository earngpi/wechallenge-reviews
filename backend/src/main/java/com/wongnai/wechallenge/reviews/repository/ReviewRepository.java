package com.wongnai.wechallenge.reviews.repository;

import com.wongnai.wechallenge.reviews.model.Review;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

import java.util.Set;

@EnableElasticsearchRepositories
@Repository
public interface ReviewRepository extends ElasticsearchRepository<Review, Integer> {

    Review findReviewByReviewId(Integer reviewId);

    @Query("{\"match_phrase\": {\"review\": \"?0\"}}")
    Set<Review> findReviewsByFoodName(String foodName);

}
