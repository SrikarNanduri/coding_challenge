package com.example.codingchallenge.util;

import com.example.codingchallenge.model.Cart;
import com.example.codingchallenge.model.CheckOutSummary;

public interface ICartCalculator {
    public CheckOutSummary calculate(Cart cart);
}
