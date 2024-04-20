package com.neb.nebotools.controller.page.invoice;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.neb.nebotools.controller.ControllerListAbstract;
import com.neb.nebotools.dao.DaoFactory;
import com.neb.nebotools.model.Customer;
import com.neb.nebotools.model.Employee;
import com.neb.nebotools.model.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InvoiceListController extends ControllerListAbstract<Invoice> {


    @FXML
    private TableColumn<Invoice, Customer> columnCustomer;

    @FXML
    private TableColumn<Invoice, Employee> columnEmployee;

    @FXML
    private TableColumn<Invoice, String> columnInvoice;

    @FXML
    private TableColumn<Invoice, Integer> columnPrice;

    @FXML
    private TableColumn<Invoice, Date> columnDate;

    @Override
    protected void setDao() throws SQLException {
        dao = DaoFactory.getInvoiceDao();
    }

    @Override
    protected void initTable() {
        columnCustomer.setCellValueFactory(new PropertyValueFactory<Invoice, Customer>("customer"));
        columnEmployee.setCellValueFactory(new PropertyValueFactory<Invoice, Employee>("employee"));
        columnInvoice.setCellValueFactory(new PropertyValueFactory<Invoice, String>("id"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("price"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Invoice, Date>("dateString"));
    }

    @Override
    protected void printSelection() throws SQLException, exception.EntityNotFoundException, FileNotFoundException {
        String path = "/home/rocks/IdeaProjects/NeboTools/invoice.pdf";

        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        pdfDocument.setDefaultPageSize(PageSize.A4);

        float col = 280f;
        float columnwidth[] = {col, col};
        Table table = new Table(columnwidth);

        table.setBackgroundColor(new DeviceRgb(-63, 169, 219))
                .setFontColor(Color.WHITE);

        table.addCell(new Cell().add("Report Du " + DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy", new Locale("FR", "fr")).format(LocalDate.now()))
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setFontSize(15f)
                .setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add("Fusion Ets")
                .setMarginTop(30f)
                .setBorder(Border.NO_BORDER)
                .setMarginRight(15f)
                .setTextAlignment(TextAlignment.RIGHT)
        );

        table.addCell(new Cell().add("INVOICE NUMBER: " + getSelected().size())
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setFontSize(15f)
                .setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add("Situé a pk14")
                .setBorder(Border.NO_BORDER)
                .setMarginRight(15f)
                .setTextAlignment(TextAlignment.RIGHT)
        );

        table.addCell(new Cell()
                .setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add("Tel:(+237) 6 90 18 53 35")
                .setBorder(Border.NO_BORDER)
                .setMarginRight(15f)
                .setTextAlignment(TextAlignment.RIGHT)
        );
        float colWith[] = {1000};
        Table customerInterforTable = new Table(colWith);
        customerInterforTable.addCell(new Cell(0, 6)
                .add("Informations INVOICES")
                .setTextAlignment(TextAlignment.CENTER)
                .setMargin(10)
                .setBold()
        );

        float itemInfocolWidth[] = {140, 140, 140, 140, 140};
        Table itemInfoTable = new Table(itemInfocolWidth);

        itemInfoTable.addCell(new Cell()
                .add("INVOICE")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
        );
        itemInfoTable.addCell(new Cell()
                .add("CUSTOMER")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
        );

        itemInfoTable.addCell(new Cell()
                .add("EMPLOYEE")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));
        itemInfoTable.addCell(new Cell()
                .add("PRICE")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));

        itemInfoTable.addCell(new Cell()
                .add("DATE")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));


        for (Invoice obj : getSelected()) {

            itemInfoTable.addCell(new Cell().add(obj.getId()));
            itemInfoTable.addCell(new Cell().add(obj.getCustomer().getLastname() + " " + obj.getCustomer().getFirstname()));
            itemInfoTable.addCell(new Cell().add(obj.getEmployee().getLastname() + " " + obj.getEmployee().getFirstname()));
            itemInfoTable.addCell(new Cell().add(obj.getPrice() + " CFA").setTextAlignment(TextAlignment.RIGHT));
            itemInfoTable.addCell(new Cell().add(obj.getDateString()));

        }

        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInterforTable);
        document.add(itemInfoTable);
        document.close();
        System.out.println("pdf created");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("Impression effectuée!!");
        alert.setHeaderText("Information");
        alert.showAndWait();
    }

    @Override
    public List<Invoice> search() {
        return null;
    }

    @Override
    protected void initComponent() throws SQLException {

    }
}
