package com.neb.nebotools.model;

import java.sql.Date;

public class Lot extends AbstractEntity{

  	private String product;
  	private String num_lot;
  	private int quantity;
    private String variant;
  	private Date delivery_date;
    private Date expiration_date;
    private String supplier;
  	private int price;
      /*
      * 0 for pending
      * 1 for success
      * -1 for fail*/
    private int status;

    public Lot(String id, String product, String num_lot, int quantity, String variant, Date delivery_date, Date expiration_date, String supplier, int price, int status, int is_actif) {
        super(id, is_actif);
        this.product = product;
        this.num_lot = num_lot;
        this.quantity = quantity;
        this.variant = variant;
        this.delivery_date = delivery_date;
        this.expiration_date = expiration_date;
        this.supplier = supplier;
        this.price = price;
        this.status = status;
    }
    public Lot(String product, String num_lot, int quantity, String variant, Date delivery_date, Date expiration_date, String supplier, int price, int status) {
        this.product = product;
        this.num_lot = num_lot;
        this.quantity = quantity;
        this.variant = variant;
        this.delivery_date = delivery_date;
        this.expiration_date = expiration_date;
        this.supplier = supplier;
        this.price = price;
        this.status = status;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getNum_lot() {
        return num_lot;
    }

    public void setNum_lot(String num_lot) {
        this.num_lot = num_lot;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void setEntity(AbstractEntity t) {

    }
}
