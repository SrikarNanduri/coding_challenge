package com.example.codingchallenge.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class CheckOutSummary {
    Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public float getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(float discountAmt) {
        this.discountAmt = discountAmt;
    }

    public float getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(float totalAmt) {
        this.totalAmt = totalAmt;
    }

    public float getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(float netAmt) {
        this.netAmt = netAmt;
    }

    List<Promotion> promotions = new ArrayList<>();
    float discountAmt;
    float totalAmt;
    float netAmt;
}
