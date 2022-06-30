package com.example.wisley.academia.api.utils;

import com.example.wisley.academia.api.model.TreinoPago;
import com.itextpdf.text.DocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Component
public class GeradorExcelUtils {

    public static File gerarRelatorioExcel(List<TreinoPago> treinoPagos) throws IOException, DocumentException {
        File fileReports = gerarDocumento(treinoPagos);
        return fileReports;
    }
    public static File gerarDocumento(List<TreinoPago> treinosPagos) throws IOException, DocumentException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetTrenosPagos = workbook.createSheet("Treinos Pagos");
        int rowNum = 0;
        int headerCellNum = 0;
        Row headerRow = sheetTrenosPagos.createRow(rowNum++);
        Cell cellTreino = headerRow.createCell(headerCellNum++);
        cellTreino.setCellValue("Treino");
        Cell cellUsuario = headerRow.createCell(headerCellNum++);
        cellUsuario.setCellValue("Usuário");
        Cell cellData = headerRow.createCell(headerCellNum++);
        cellData.setCellValue("Data");

        for (TreinoPago treinoPago : treinosPagos) {
            int bodyCellNum = 0;
            Row rowBody = sheetTrenosPagos.createRow(rowNum++);
            cellTreino = rowBody.createCell(bodyCellNum++);
            cellTreino.setCellValue(treinoPago.getTreino().getDescricao());
            cellUsuario = rowBody.createCell(bodyCellNum++);
            cellUsuario.setCellValue(treinoPago.getUsuario().getNome());
            cellData = rowBody.createCell(bodyCellNum++);
            cellData.setCellValue(treinoPago.getDataHoraRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        File fileExcel = File.createTempFile("TREINOS_PAGOS_" + uuidAsString,".xlsx");
        if (!fileExcel.exists()){
            fileExcel.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(fileExcel);
        workbook.write(out);
        out.close();
        return fileExcel;
    }
}
