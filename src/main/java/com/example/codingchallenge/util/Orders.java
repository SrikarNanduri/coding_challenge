package com.example.codingchallenge.util;

import com.example.codingchallenge.exception.ShoppingCartException;
import com.example.codingchallenge.model.CheckOutSummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Orders{

   private static HashMap<String, List<CheckOutSummary>>  applicationOrders;


    public static HashMap<String, List<CheckOutSummary>> getApplicationOrders(){
        if(applicationOrders == null){
            applicationOrders = new HashMap<String,List<CheckOutSummary>>();
        }
        return applicationOrders;
    }

    public static void addOrder(String userId, CheckOutSummary checkOutSummary) throws ShoppingCartException {
        if(applicationOrders.containsKey(userId)){
            List<CheckOutSummary> existingOrders = applicationOrders.get(userId);
            existingOrders.add(checkOutSummary);
        }else{
            List<CheckOutSummary> list= new ArrayList<CheckOutSummary>();
            list.add(checkOutSummary);
            applicationOrders.put(userId, list );
        }

    }
}
