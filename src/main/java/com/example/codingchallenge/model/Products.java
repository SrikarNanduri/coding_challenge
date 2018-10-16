package com.example.codingchallenge.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class Products {

    public String id;
    public float price;
    public Promotion[] promotions;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public com.example.codingchallenge.model.Promotion[] getPromotions() {
        return promotions;
    }

    public String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setPromotions(com.example.codingchallenge.model.Promotion[] promotions) {
        this.promotions = promotions;
    }


}
