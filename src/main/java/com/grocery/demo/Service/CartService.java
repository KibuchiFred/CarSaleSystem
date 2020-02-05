package com.grocery.demo.Service;

import com.grocery.demo.Model.CartItems;
import com.grocery.demo.Model.Product;
import com.grocery.demo.Repository.CartItemsRepository;
import com.grocery.demo.Repository.ProductRepository;
import io.netty.util.internal.MathUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class CartService {
    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private ProductRepository productRepository;


    public void addToCart(Long carId, CartItems cartItems, String username){

    Product product = productRepository.findById(carId).orElse(null);
        cartItems.setProduct(product);

            cartItems.setSubTotal(product.getPrice());
            cartItems.setUsername(username);

            cartItemsRepository.save(cartItems);

    }
    public List<CartItems> myCart(String userName){

        List<CartItems> cartItems = new ArrayList<>();
        cartItemsRepository.findByUsername(userName).forEach(cartItems::add);

        List<Double> getPrices = cartItemsRepository.allPrices(userName);

       int sum = 0;
        for (double items : getPrices){
            sum += items;
        }
        System.out.println("The total cart Items Price is: "+sum);


        return cartItems;
    }
}
