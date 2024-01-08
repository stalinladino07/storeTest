package com.test.store.services;

import com.test.store.repository.OrderRepository;
import com.test.store.repository.ProductOrderRepository;
import com.test.store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;

    public Orders createOrder (CreateOrder order) {
        try {

        Orders createOrder = new Orders();
        createOrder.setDateOrder(order.getDateOrder());
        Orders newOrder = orderRepository.save(createOrder);

        for (ProductSelect product : order.getProducts()) {
            ProductOrder createProd = new ProductOrder();
            createProd.setIdOrder(newOrder.getIdOrder());
            createProd.setIdProduct(product.getIdProduct());
            productOrderRepository.save(createProd);
        }
            return newOrder;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public List<OrderClient> getByOrder(Long id, LocalDate dateInit, LocalDate dateEnd) {

        List<Tuple> tuples = productOrderRepository.getOrderByClient(id, dateInit, dateEnd);
        List<OrderClient> resultados = new ArrayList<>();
        for (Tuple tuple : tuples) {
            OrderClient stockStore = new OrderClient();
            stockStore.setDateOrder(tuple.get("dateorder", Date.class));
            stockStore.setStoreName(tuple.get("storeName", String.class));
            stockStore.setProductName(tuple.get("productName", String.class));

            BigDecimal availableAsBigDecimal = tuple.get("price", BigDecimal.class);
            Double availableAsDouble = availableAsBigDecimal != null ? availableAsBigDecimal.doubleValue() : null;
            stockStore.setPrice(availableAsDouble);

            resultados.add(stockStore);
        }
        return resultados;
    }

    public ResponseEntity<Response> deleteById (Long idOrder) {
        Response resp = new Response();
        if (orderRepository.existsById(idOrder)) {

            productOrderRepository.deleteProductOrder(idOrder);
            orderRepository.deleteById(idOrder);

            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Orden eliminado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró la Order con ID: " + idOrder);
            return ResponseEntity.badRequest().body(resp);
        }
    }
}
