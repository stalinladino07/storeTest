package com.test.store.controller;

import com.test.store.model.CreateOrder;
import com.test.store.model.OrderClient;
import com.test.store.services.OrderService;
import com.test.store.model.Orders;
import com.test.store.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
        RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping ("/api/order")

public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    @ResponseBody
    private ResponseEntity<Orders> createOrders (@RequestBody CreateOrder order){
        Orders ordersCreate = orderService.createOrder(order);
        try {
            return ResponseEntity.created(new URI("/api/hulkstore/save"+ordersCreate.getIdOrder())).body(ordersCreate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/getbystore/{id}")
    private ResponseEntity<List<OrderClient>> getStockByStore (@PathVariable Long id,
                                                               @RequestParam(name = "dateInit")
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateInit
            , @RequestParam(name = "dateEnd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd){
        List<OrderClient> stockResponse = orderService.getByOrder(id, dateInit, dateEnd);
        try {
            return ResponseEntity.ok(stockResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> deleteOrder (@PathVariable Long id){

        try {
            return orderService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };
}
