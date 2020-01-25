package com.grocery.demo.Repository;


import com.grocery.demo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
List<Product> findByStatus(String carStatus);
List<Product> findByDealerName(String dealerName);

@Modifying
    @Query(value = "update Product p set p.status = :status where  p.carId= :carId")
    void setUpdateStatus (@Param("status") String status, @Param("carId") Long carId);

    @Modifying
    @Query(value = "delete FROM Product p  where  p.carId = :carId")
    void deleteProduct (@Param("carId") Long carId);
}
