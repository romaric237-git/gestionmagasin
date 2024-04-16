package com.neb.nebotools.model;

import com.neb.nebotools.dao.DaoFactory;
import lombok.*;

import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceLine extends AbstractEntity{

    private String invoice;
    private String product;
    private int quantity;
    private int price;

    public InvoiceLine(String id, String invoice, String product, int quantity, int price, int is_actif) {
        super(id, is_actif);
        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Invoice getInvoice() throws SQLException, exception.EntityNotFoundException {
        return DaoFactory.getInvoiceDao().find(invoice);
    }

    public String getInvoiceString() throws SQLException, exception.EntityNotFoundException {
        return invoice;
    }

    public Product getProduct() throws SQLException, exception.EntityNotFoundException {
        return DaoFactory.getProductDao().find(product);
    }
    @Override
    public void setEntity(AbstractEntity t) {

    }
}
