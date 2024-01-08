package com.test.store.repository;

import com.test.store.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query(value = "SELECT * FROM stock WHERE idProduct=:idProduct ", nativeQuery = true)
    Stock getProductById(Long idProduct);

    @Query(value = "SELECT store.name as storeName, product.name as productName, product.code  as productCode, stock.idstock, stock.available, stock.sold, product.price FROM stock, product, store WHERE stock.idProduct = product.idProduct AND product.idStore = store.idStore AND product.idStore = :idStore", nativeQuery = true)
    List<Tuple> getStockByStore(Long idStore);
}
