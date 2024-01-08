package com.test.store.model;

import java.sql.Date;
import java.util.List;

public class CreateOrder {

    private Long idStore;
    private Date dateOrder;
    private List<ProductSelect> products;

    public Long getIdStore() {
        return idStore;
    }

    public void setIdStore(Long idStore) {
        this.idStore = idStore;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<ProductSelect> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSelect> products) {
        this.products = products;
    }
}