package com.test.store.services;

import com.test.store.model.Store;
import com.test.store.model.Response;
import com.test.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store create (Store store) {
        return storeRepository.save(store);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll(Sort.by(Sort.Direction.ASC, "idStore"));
    }

    public void delete (Store store) {
         storeRepository.delete(store);
    }

    public ResponseEntity<Response> update (Long idStore, Store store) {
        Response resp = new Response();
        if (storeRepository.existsById(idStore)) {
            store.setIdStore(idStore);
            storeRepository.save(store);
            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Storeo actualizado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró el storeo con ID: " + idStore);
            return ResponseEntity.badRequest().body(resp);
        }
    }

    public Optional<Store> getById (Long id) {
        return storeRepository.findById(id);
    }


    public ResponseEntity<Response> deleteById (Long idStore) {
        Response resp = new Response();
        if (storeRepository.existsById(idStore)) {
            storeRepository.deleteById(idStore);
            resp.setStatus(HttpStatus.OK.toString());
            resp.setMessage("Storeo eliminado correctamente");
            return ResponseEntity.ok(resp);
        } else {
            resp.setStatus(HttpStatus.BAD_REQUEST.toString());
            resp.setMessage("No se encontró el storeo con ID: " + idStore);
            return ResponseEntity.badRequest().body(resp);
        }
    }

}
