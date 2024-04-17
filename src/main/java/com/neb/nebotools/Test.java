package com.neb.nebotools;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;



import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        createMakamPdf();
    }

    private static void createMakamPdf() throws FileNotFoundException {
        String path = "/home/rocks/IdeaProjects/NeboTools/src/main/resources/generated/file1.pdf";

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

        table.addCell(new Cell().add("Nombre de clients: 50")
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
                .add("Informations Clients")
                .setTextAlignment(TextAlignment.CENTER)
                .setMargin(10)
                .setBold()
        );

        float itemInfocolWidth[] = {140, 140, 140, 140};
        Table itemInfoTable = new Table(itemInfocolWidth);

        itemInfoTable.addCell(new Cell()
                .add("NOM")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
        );
        itemInfoTable.addCell(new Cell()
                .add("CATEGORIE")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE)
        );


        itemInfoTable.addCell(new Cell()
                .add(" QUANTITE")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));
        itemInfoTable.addCell(new Cell()
                .add("PRIX")
                .setBackgroundColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.WHITE));
        for (int i = 0; i < 50; i++) {
            itemInfoTable.addCell(new Cell().add("Mars"));
            itemInfoTable.addCell(new Cell().add("500"));
            itemInfoTable.addCell(new Cell().add(String.valueOf("30")));
            itemInfoTable.addCell(new Cell().add(String.valueOf("70")));
        }

        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInterforTable);
        document.add(itemInfoTable);
        document.close();
        System.out.println("pdf created");

    }

    public static void createCustomSize() throws FileNotFoundException {
        String path = "/home/rocks/IdeaProjects/NeboTools/src/main/resources/generated/file1.pdf";
        Rectangle size = new Rectangle(200, 5000);
        PdfWriter PdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(PdfWriter);
        Document document = new Document(pdfDocument, new PageSize(size));

        Paragraph paragraph = new Paragraph("Yo");
        document.add(paragraph);
        document.close();
    }

    public static void createTable() throws FileNotFoundException {
        String path = "/home/rocks/IdeaProjects/NeboTools/src/main/resources/generated/file1.pdf";
        PdfWriter PdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(PdfWriter);
        Document document = new Document(pdfDocument);

        float columnWidth[] = {200f, 50f, 100f};
        Table table = new Table(columnWidth);

        table.addCell(new Cell().add("Item"));
        table.addCell(new Cell().add("Qty"));
        table.addCell(new Cell().add("Available"));

        table.addCell(new Cell().add("Mango"));
        table.addCell(new Cell().add("2 Kg"));
        table.addCell(new Cell().add("Yes"));

        table.addCell(new Cell().add("Orange"));
        table.addCell(new Cell().add("5 Kg"));
        table.addCell(new Cell().add("No"));

        document.add(table);


        document.close();
    }

    public static void createParagraph() throws FileNotFoundException {
        String path = "/home/rocks/IdeaProjects/NeboTools/src/main/resources/generated/file1.pdf";
        String paraText = "There are many variations of passages of Lorem Ipsum available, " +
                "but the majority have suffered alteration in some form, " +
                "by injected humour, " +
                "or randomised words which don't look even slightly believable. " +
                "If you are going to use a passage of Lorem Ipsum, " +
                "you need to be sure there isn't anything embarrassing hidden in the middle of text. " +
                "All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, " +
                "making this the first true generator on the Internet. " +
                "It uses a dictionary of over 200 Latin words, " +
                "combined with a handful of model sentence structures, " +
                "to generate Lorem Ipsum which looks reasonable. " +
                "The generated Lorem Ipsum is therefore always free from repetition, " +
                "injected humour, or non-characteristic words etc.";
        Paragraph paragraph1 = new Paragraph(paraText);
        PdfWriter PdfWriter = new PdfWriter(path);

        PdfDocument pdfDocument = new PdfDocument(PdfWriter);
        pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);
        document.add(paragraph1);
        document.close();
    }

    public static void createPdfBlank() throws FileNotFoundException {
        String path = "/home/rocks/IdeaProjects/NeboTools/src/main/resources/generated/file1.pdf";
        PdfWriter PdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(PdfWriter);
        pdfDocument.addNewPage();
        Document document = new Document(pdfDocument);
        document.close();
    }
}
