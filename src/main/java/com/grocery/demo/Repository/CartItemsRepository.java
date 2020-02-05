package com.grocery.demo.Repository;

import com.grocery.demo.Model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    List<CartItems> findByUsername(String userName);

    @Modifying
    @Query("SELECT  c.subTotal FROM CartItems c where c.username = :username")
    List<Double> allPrices(@Param("username") String username);

}
