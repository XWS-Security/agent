package com.example.agent.report;

import com.example.agent.model.CampaignReport;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReportPDFExporter {
    @Value("${PDF_REPORTS_PATH}")
    private String folderPath;
    private List<CampaignReport> reports;

    public void setReports(List<CampaignReport> reports) {
        this.reports = reports;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Report ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date of exposure", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Number of likes", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Number of dislikes", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Number of comments", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Number of revenue", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Number of clicks per user", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Number of views", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (var report : reports) {
            table.addCell(String.valueOf(report.getId()));
            table.addCell(report.getDate());
            table.addCell(String.valueOf(report.getLikes()));
            table.addCell(String.valueOf(report.getDislikes()));
            table.addCell(String.valueOf(report.getComments()));
            table.addCell(String.valueOf(report.getRevenue()));
            table.addCell(report.getClicks());
            table.addCell(String.valueOf(report.getViews()));
        }
    }

    public ByteArrayInputStream export() throws DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(14);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Reports", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
