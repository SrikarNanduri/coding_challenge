package com.example.codingchallenge.util;

import com.example.codingchallenge.exception.ShoppingCartException;
import com.example.codingchallenge.model.Cart;
import com.example.codingchallenge.model.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

public class ShoppingCart {
  private static   HashMap<String, Cart> applicationCart = null;

  public static HashMap<String, Cart> getApplicationCart(){
        if(applicationCart == null){
            applicationCart = new HashMap<>();
        }
        return applicationCart;
    }

  public static void addToUserCart(String userId, CartItem[] cartItems) throws ShoppingCartException {
        Cart userCart = null;
        if(applicationCart.containsKey(userId)){
            userCart = applicationCart.get(userId);
            List<CartItem> mergedCartList = new ArrayList<CartItem>();
            mergedCartList.addAll(Arrays.asList(cartItems));
            mergedCartList.addAll(userCart.getCartItems());
            userCart.setCartItems(mergedCartList);
        } else {
            userCart = new Cart();
            userCart.setCartItems(Arrays.asList(cartItems));

        }
        applicationCart.put(userId, userCart);
  }

}
