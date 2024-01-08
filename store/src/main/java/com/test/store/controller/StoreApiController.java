package com.test.store.controller;

import com.test.store.model.Store;
import com.test.store.model.Response;
import com.test.store.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
        RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping ("/api/store")
public class StoreApiController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/save")
    @ResponseBody
    private ResponseEntity<Store> saveStore (@RequestBody Store store){
        Store storeCreate = storeService.create(store);

        try {
            return ResponseEntity.created(new URI("/api/hulkstore/save"+storeCreate.getIdStore())).body(storeCreate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/listall")
    private ResponseEntity<List<Store>> getAllStores (){
        List<Store> storeListAll = storeService.getAllStores();
        try {
            return ResponseEntity.ok(storeListAll);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @DeleteMapping("/{id}")
    private ResponseEntity<Response> deleteStore (@PathVariable Long id){

        try {
            return storeService.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping(name="/getbyid/{id}", value = "{id}")
    private ResponseEntity<Optional<Store>> findById (@PathVariable Long id){
        Optional<Store> storeResponse = storeService.getById(id);
        try {
            return ResponseEntity.ok(storeResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @PutMapping("/{id}")
    @ResponseBody
    private ResponseEntity<Response> updateStore (@PathVariable Long id, @RequestBody Store store){
        try {
            return storeService.update(id,store);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

}
