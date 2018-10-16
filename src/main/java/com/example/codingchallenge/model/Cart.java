package com.example.codingchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class Cart {
    public List<com.example.codingchallenge.model.CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<com.example.codingchallenge.model.CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    List<CartItem> cartItems = new ArrayList<>();

}
