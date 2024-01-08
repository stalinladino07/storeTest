package com.test.store.model;

import javax.persistence.*;

@Entity
@Table(name = "Stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstock")
    private Long idStock;

    @Column(name = "idproduct")
    private Long idProduct;

    @Column(name = "available")
    private Double available;

    @Column(name = "sold")
    private Double sold;

    public Long getIdStock() {
        return idStock;
    }

    public void setIdStock(Long idStock) {
        this.idStock = idStock;
    }

    public Double getAvailable() {

        return available;
    }

    public void setAvailable(Double available) {
        this.available = available;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {

        this.sold = sold;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

}
