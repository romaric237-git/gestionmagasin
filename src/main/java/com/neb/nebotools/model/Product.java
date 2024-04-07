package com.neb.nebotools.model;

public class Product extends AbstractEntity {

    private String name;
    private String barcode;
    private String category;
    private String description;
    private int base_price;
    private int min_price;

    public Product(String id, String name, String barcode, String category, String description, int base_price, int min_price, int is_actif) {
        super(id, is_actif);
        setName(name);
        setBarcode(barcode);
        setCategory(category);
        setDescription(description);
        setMin_price(min_price);
        setBase_price(base_price);
    }

    public Product(String name, String barcode, String category, String description, int base_price, int min_price) {
        this("", name, barcode, category, description, base_price, min_price, 1);
    }

    public Product() {
        this("", "", "", "", "", 0, 0, 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBase_price() {
        return base_price;
    }

    public void setBase_price(int base_price) {
        if (base_price < min_price)
            throw new IllegalStateException("Le prix doit etre superieur au prix minimum");
        this.base_price = base_price;
    }

    public int getMin_price() {
        return min_price;
    }

    public void setMin_price(int min_price) {
        if (min_price < 0)
            throw new IllegalStateException("Le prix ne'st pas negatif");
        this.min_price = min_price;
    }

    @Override
    public void setEntity(AbstractEntity t) {
        if (t instanceof Product) {
            Product p = (Product) t;
            setId(p.id);
            setIs_actif(p.is_actif);
            setName(p.name);
            setBarcode(p.barcode);
            setCategory(p.category);
            setDescription(p.description);
            setMin_price(p.min_price);
            setBase_price(p.base_price);
        }
    }
}
