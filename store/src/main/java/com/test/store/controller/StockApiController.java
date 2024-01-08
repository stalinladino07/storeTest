package com.test.store.controller;

import com.test.store.model.Stock;
import com.test.store.model.StockStore;
import com.test.store.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
        RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping ("/api/stock")
public class StockApiController {

    @Autowired
    private StockService stockService;

    @PostMapping("/save")
    @ResponseBody
    private ResponseEntity<Stock> saveStock (@RequestBody Stock stock){
        Stock stockCreate = stockService.create(stock);

        try {
            return ResponseEntity.created(new URI("/api/stock/save"+stockCreate.getIdStock())).body(stockCreate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/listall")
    private ResponseEntity<List<Stock>> listAllStock (){
        List<Stock> stockListAll = stockService.getAllStock();
        try {
            return ResponseEntity.ok(stockListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteStock (@PathVariable Long id){
        stockService.deleteById(id);
        try {
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };


    @GetMapping("/getbystore/{id}")
    private ResponseEntity<List<StockStore>> getStockByStore (@PathVariable Long id){
        List<StockStore> stockResponse = stockService.getStockByStore(id);
        try {
            return ResponseEntity.ok(stockResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };
}
