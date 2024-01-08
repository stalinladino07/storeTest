package com.test.store.repository;

import com.test.store.model.OrderClient;
import com.test.store.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query(value = "SELECT * FROM product WHERE product.idstore = :idStore ", nativeQuery = true)
    List<OrderClient> getOrdersByStore(Long idStore);
}
