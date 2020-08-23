package com.wongnai.wechallenge.reviews.repository;

import com.wongnai.wechallenge.reviews.model.Review;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

@EnableElasticsearchRepositories
@Repository
public interface ReviewRepository extends ElasticsearchRepository<Review, Integer> {

    Review findReviewByReviewId(Integer reviewId);

}
