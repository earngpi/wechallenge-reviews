package com.wongnai.wechallenge.reviews.repository;

import com.wongnai.wechallenge.reviews.model.Food;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

@EnableElasticsearchRepositories
@Repository
public interface FoodRepository extends ElasticsearchRepository<Food, String> {

    Food findFoodByFoodNameEquals(String foodName);

}
