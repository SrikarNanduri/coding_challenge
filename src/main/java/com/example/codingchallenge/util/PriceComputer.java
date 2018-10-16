package com.example.codingchallenge.util;

import com.example.codingchallenge.model.Products;

public class PriceComputer {

    public float getPrice(Products product, int cartQty){

        float price = product.getPrice();
        if(product.getPromotions() != null && product.getPromotions().length >0){
            for (int i = 0; i < product.getPromotions().length ; i++) {
                if(product.getPromotions()[i].getType().equalsIgnoreCase("FLAT_PERCENT") ){
                    price -= product.getPromotions()[i].getAmount();
                }
                if(product.getPromotions()[i].getType().equalsIgnoreCase("QTY_BASED_PRICE_OVERRIDE") &&
                        product.getPromotions()[i].getRequired_qty() == cartQty){
                        price = (float) (product.getPromotions()[i].getPrice()) / cartQty;
                }
                if(product.getPromotions()[i].getType().equalsIgnoreCase("BUY_X_GET_Y_FREE")  &&
                        product.getPromotions()[i].getRequired_qty() == cartQty){
                        price = (float) product.getPrice()/cartQty;
                }
            }

        }
        return price;
    }
}
