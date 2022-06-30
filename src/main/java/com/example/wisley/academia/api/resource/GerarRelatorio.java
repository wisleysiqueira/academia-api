package com.example.wisley.academia.api.resource;

import com.example.wisley.academia.api.model.TreinoPago;
import com.example.wisley.academia.api.repository.TreinoPagoRepository;
import com.example.wisley.academia.api.repository.filter.RelatorioFilter;
import com.example.wisley.academia.api.utils.GeradorExcelUtils;
import com.example.wisley.academia.api.utils.GeradorPDFUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/gerar-relario")
public class GerarRelatorio {

    @Autowired
    private GeradorPDFUtils geradorPDFUtils;

    @Autowired
    private GeradorExcelUtils geradorExcelUtils;

    @Autowired
    TreinoPagoRepository treinoPagoRepository;

    @GetMapping(value = "/relatorio-mensal-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] gerarRelatorioPdf(RelatorioFilter relatorioFilter) throws Exception {
        if (relatorioFilter.getAno() == 0){
            throw new Exception("É necessário informar o ano válido");
        }
        if(relatorioFilter.getMes() < 1 || relatorioFilter.getMes() > 12){
            throw new Exception("É necessário informar o mês válido");
        }
        Date data = new Date(relatorioFilter.getAno(), relatorioFilter.getMes() - 1, 1);
        Locale local = new Locale("pt", "BR");
        DateFormat mesFormat = new SimpleDateFormat("MMMM", local);
        String mesPortugues = mesFormat.format(data);
        String nomeRelatorio = "Relatório Treinos Pagos de " + mesPortugues + " de " + relatorioFilter.getAno();
        List<TreinoPago> treinosPagos = treinoPagoRepository.listarPorMesAno(relatorioFilter.getMes(), relatorioFilter.getAno());
        File relatorioPdf = geradorPDFUtils.gerarRelatorioMensalPdf(nomeRelatorio, treinosPagos);
        InputStream in = new FileInputStream(relatorioPdf.getAbsolutePath());
        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "/relatorio-mensal-excel")
    public ResponseEntity<String> gerarRelatorioExcel(RelatorioFilter relatorioFilter) throws Exception {
        if (relatorioFilter.getAno() == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("É necessário informar o ano válido");
        }
        if(relatorioFilter.getMes() < 1 || relatorioFilter.getMes() > 12){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("É necessário informar o mês válido");
        }
        List<TreinoPago> treinosPagos = treinoPagoRepository.listarPorMesAno(relatorioFilter.getMes(), relatorioFilter.getAno());
        File relatorioPdf = geradorExcelUtils.gerarRelatorioExcel(treinosPagos);
        if(relatorioPdf.getAbsolutePath() != null){
            return ResponseEntity.ok(relatorioPdf.getAbsolutePath());
        } else {
            throw new Exception("Erro ao gerar relatório excel");
        }
    }
}
