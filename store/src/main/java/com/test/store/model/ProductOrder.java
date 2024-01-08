package com.test.store.model;

import javax.persistence.*;

@Entity
@Table (name = "product_order")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproductorder")
    private Long idProductOrder;

    @Column(name = "idproduct")
    private Long idProduct;

    @Column(name = "idorder")
    private Long idOrder;

    public Long getIdProductOrder() {
        return idProductOrder;
    }

    public void setIdProductOrder(Long idProductOrder) {
        this.idProductOrder = idProductOrder;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }
}
