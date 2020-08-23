package com.wongnai.wechallenge.reviews.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.lang.NonNull;

@Document(indexName = "food_dictionary")
public class Food {

    @Id
    private int foodId;
    @NonNull
    private String foodName;

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
