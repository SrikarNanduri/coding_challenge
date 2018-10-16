package com.example.codingchallenge.shopping;

import com.example.codingchallenge.exception.ShoppingCartException;
import com.example.codingchallenge.model.Cart;
import com.example.codingchallenge.model.CartItem;
import com.example.codingchallenge.model.CheckOutSummary;
import com.example.codingchallenge.model.Products;
import com.example.codingchallenge.util.CartCalculator;
import com.example.codingchallenge.util.ICartCalculator;
import com.example.codingchallenge.util.Orders;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;

import static com.example.codingchallenge.util.Orders.getApplicationOrders;
import static com.example.codingchallenge.util.ShoppingCart.addToUserCart;
import static com.example.codingchallenge.util.ShoppingCart.getApplicationCart;


@RestController
public class ProductsController {

    @Bean
    ICartCalculator cartCalculator(){
        return new CartCalculator();
    }

    HashMap<String, Cart> applicationCart = getApplicationCart();
    HashMap<String, List<CheckOutSummary>> applicationOrders = getApplicationOrders();

    @ApiOperation(value = " Add to cart",response = Cart.class)
    @RequestMapping(value="cart",  method = RequestMethod.POST)
    public void addToCart (@RequestParam("userid") String  userId, @RequestBody CartItem[] cartItems) throws ShoppingCartException {
        // Using applicationCart as in memory hashmap to save carts of all users. Ideally should be a database or other cache.
        addToUserCart(userId,cartItems);
    }

    @ApiOperation(value = " cart checkout",response = Products.class)
    @RequestMapping(value="checkout/{id}",  method = RequestMethod.GET)
    public CheckOutSummary checkOut(@PathVariable String id) throws ShoppingCartException{
        if(applicationCart.get(id) == null){
            throw new ShoppingCartException("User Cart not found");
        }
           // Calculate the totals
            CheckOutSummary checkOutSummary =  cartCalculator().calculate( applicationCart.get(id));
            // remove user from the application cart
            applicationCart.remove(id);
            // Assuming that order is placed after checkout, add the cart details to order
           // Using in memory hashmap to save all orders that are placed using the application. Ideally this should be a database or other caches
            Orders.addOrder(id, checkOutSummary);
            return checkOutSummary;
    }


    @ApiOperation(value = " Get order History",response = Products.class)
    @RequestMapping(value="order/user/{id}",  method = RequestMethod.GET)
    public List<CheckOutSummary> getOrder(@PathVariable String id) throws ShoppingCartException{
        if (Orders.getApplicationOrders().get(id) == null ) {
            throw new ShoppingCartException("User Cart not found");
        }
        return Orders.getApplicationOrders().get(id);
    }

}
