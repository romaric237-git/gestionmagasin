package com.neb.nebotools.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product extends AbstractEntity {

    private String name;
    private String barcode;
    private String brand;
    private String category;
    private String description;
    private int base_price;
    private int min_price;
    private int stock;
    private int quantity;
    private int minimum;
    private String status;

    public Product(String id, String name, String brand, String barcode, String category, String description, int base_price, int min_price, int stock, int quantity, int minimum, int is_actif) {
        super(id, is_actif);
        setBrand(brand);
        setName(name);
        setBarcode(barcode);
        setCategory(category);
        setDescription(description);
        setMin_price(min_price);
        setBase_price(base_price);
        setStock(stock);
        setQuantity(quantity);
        if (quantity <= 20)
            setStatus("Insuffisant");
        else
            setStatus("Suffisant");
    }

    public void setBase_price(int base_price) {
        if (base_price < min_price)
            throw new IllegalStateException("Le prix doit etre superieur au prix minimum");
        this.base_price = base_price;
    }

    public void setMin_price(int min_price) {
        if (min_price < 0)
            throw new IllegalStateException("Le prix ne'st pas negatif");
        this.min_price = min_price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        status = quantity < minimum ? "Insuffisant" : "Suffisant";
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
        status = quantity < minimum ? "Insuffisant" : "Suffisant";
    }

    @Override
    public void setEntity(AbstractEntity t) {
        if (t instanceof Product p) {
            setId(p.id);
            setIs_actif(p.is_actif);
            setName(p.name);
            setBarcode(p.barcode);
            setCategory(p.category);
            setDescription(p.description);
            setMin_price(p.min_price);
            setBase_price(p.base_price);
            setMinimum(p.minimum);
        }
    }

    @Override
    public String toString() {
        return  name + " " + brand;
    }
}
