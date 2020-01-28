package com.grocery.demo.Repository;

import com.grocery.demo.Model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
    List<CartItems> findByUsername(String userName);

}
