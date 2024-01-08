package com.test.store.services;

import com.test.store.model.Stock;
import com.test.store.model.StockStore;
import com.test.store.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock create (Stock stock) {
        return stockRepository.save(stock);
    }

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public void delete (Stock stock) {
        stockRepository.delete(stock);
    }

    public Optional<Stock> getById (Long id) {
        return stockRepository.findById(id);
    }

    public void deleteById (Long id) {
        stockRepository.deleteById(id);
    }

    public List<StockStore> getStockByStore (Long id) {
        List<Tuple> tuples = stockRepository.getStockByStore(id);
        List<StockStore> resultados = new ArrayList<>();
        for (Tuple tuple : tuples) {
            StockStore stockStore = new StockStore();
            stockStore.setStorename(tuple.get("storeName", String.class));
            stockStore.setProductname(tuple.get("productName", String.class));
            stockStore.setProductcode(tuple.get("productCode", String.class));
            stockStore.setIdStock(tuple.get("idstock", Integer.class));

            BigDecimal availableAsBigDecimal = tuple.get("available", BigDecimal.class);
            Double availableAsDouble = availableAsBigDecimal != null ? availableAsBigDecimal.doubleValue() : null;
            stockStore.setAvailable(availableAsDouble);

            BigDecimal soldBigDecimal = tuple.get("sold", BigDecimal.class);
            Double soldAsDouble = soldBigDecimal != null ? soldBigDecimal.doubleValue() : null;
            stockStore.setSold(soldAsDouble);

            BigDecimal priceAsBigDecimal = tuple.get("price", BigDecimal.class);
            Double priceAsDouble = priceAsBigDecimal != null ? priceAsBigDecimal.doubleValue() : null;
            stockStore.setPrice(priceAsDouble);

            resultados.add(stockStore);
        }
        return resultados;
    }
}
