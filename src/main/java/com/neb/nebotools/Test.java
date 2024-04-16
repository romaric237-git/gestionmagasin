package com.neb.nebotools;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        createCustomSize();
    }

    public static void createCustomSize() throws FileNotFoundException {
        String path = "/home/rocks/IdeaProjects/NeboTools/src/main/resources/generated/file1.pdf";
        Rectangle size = new Rectangle(200,5000);
        PdfWriter PdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(PdfWriter);
        Document document = new Document(pdfDocument,new PageSize(size));

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
