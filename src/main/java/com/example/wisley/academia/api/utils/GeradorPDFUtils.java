package com.example.wisley.academia.api.utils;


import com.example.wisley.academia.api.model.TreinoPago;
import com.example.wisley.academia.api.model.TreinoPago_;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class GeradorPDFUtils {

    public static File gerarRelatorioMensalPdf(String nomeRelatorio, List<TreinoPago> treinosPagos) throws IOException, DocumentException {
        File fileReports = gerarDocumento(nomeRelatorio, treinosPagos);
        return fileReports;
    }

    public static File gerarDocumento(String nomeRelatorio, List<TreinoPago> treinosPagos) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4, 20, 20, 20, 20);

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        File tempFile = File.createTempFile("TREINOS_PAGOS_" + uuidAsString, ".pdf");
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }

        PdfWriter.getInstance(document, new FileOutputStream(tempFile.getAbsolutePath()));
        document.open();

        document.add(new Paragraph("Data:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        Paragraph titulo = new Paragraph();
        titulo.add(nomeRelatorio);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        float widths[] = { 3, 5, 2 };
        table.setWidths(widths);
        table.setHeaderRows(1);

        // add cell of table - header cell
        PdfPCell cell = new PdfPCell(new Phrase("Treino"));
        cell.setBackgroundColor(new BaseColor(0, 173, 239));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Usuário"));
        cell.setBackgroundColor(new BaseColor(0, 173, 239));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Data"));
        cell.setBackgroundColor(new BaseColor(0, 173, 239));
        table.addCell(cell);

        Phrase ph;
        for (TreinoPago treinoPago : treinosPagos) {

            cell = new PdfPCell();
            ph = new Phrase(treinoPago.getTreino().getDescricao());
            cell.addElement(ph);
            table.addCell(cell);

            cell = new PdfPCell();
            ph = new Phrase(treinoPago.getUsuario().getNome());
            cell.addElement(ph);
            table.addCell(cell);

            cell = new PdfPCell();
            ph = new Phrase(treinoPago.getDataHoraRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            cell.addElement(ph);
            table.addCell(cell);
        }
        document.add(table);
        document.close();
        return tempFile;
    }
}

