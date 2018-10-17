package com.example.codingchallenge.util;


import com.example.codingchallenge.exception.ShoppingCartException;
import com.example.codingchallenge.model.Cart;
import com.example.codingchallenge.model.CheckOutSummary;
import com.example.codingchallenge.model.Products;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class CartCalculator implements ICartCalculator {

    private static RestTemplate restTemplate = new RestTemplate();
    private static final String baseURL = "http://localhost:8081/";

    float netTotal = 0;
    float total = 0;
    CheckOutSummary checkOutSummary = new CheckOutSummary();

    public CheckOutSummary calculate(Cart cart){
      for(int i = 0; i< cart.getCartItems().size(); i++){
          Products product = null;
          try {
              product = getProductById(cart.getCartItems().get(i).getProductId());
          } catch (ShoppingCartException e) {
              e.printStackTrace();
          }
          float price = new PriceComputer().getPrice(product,cart.getCartItems().get(i).getQuantity());
          netTotal += price*cart.getCartItems().get(i).getQuantity();
          total += product.getPrice() * cart.getCartItems().get(i).getQuantity();

      }
      checkOutSummary.setCart(cart);
      checkOutSummary.setTotalAmt(total);
      checkOutSummary.setNetAmt(netTotal);
      checkOutSummary.setDiscountAmt(total - netTotal);
      return checkOutSummary;
    }



    private List<Products> getAllProducts() throws ShoppingCartException {
        ResponseEntity<Products[]> response = restTemplate.getForEntity(baseURL+"products", Products[].class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
        } else {
            System.out.println("Error connecting to the server");
            throw new ShoppingCartException("Unable to get the product catalog");
        }
    }


    private Products getProductById(String id) throws ShoppingCartException {
        ResponseEntity<Products> response = restTemplate.getForEntity(baseURL + "products/"+id, Products.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            System.out.println("Error connecting to the server");
            throw new ShoppingCartException("Unable to get the product catalog");
        }
    }
}
