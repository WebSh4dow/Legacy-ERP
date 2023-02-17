package com.developer.ERP.Legacy.API.domain.Service;

import com.developer.ERP.Legacy.API.domain.Model.Cliente;
import com.developer.ERP.Legacy.API.domain.Repository.ClienteRepository;
import com.developer.ERP.Legacy.API.infrastructure.util.DateUtils;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Comparator;

@Service
public class ClienteRelatorioService {
    @Autowired
    private ClienteRepository clienteRepository;
    public ByteArrayInputStream relatorios() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(byteArrayOutputStream));

        pdfDocument.setDefaultPageSize(PageSize.A4.rotate());
        Document document = new Document(pdfDocument);

        Paragraph titulo = new Paragraph("Relatório de Clientes")
                .setFontSize(28)
                .setFont(PdfFontFactory.createFont(StandardFonts.COURIER_BOLD))
                .setTextAlignment(TextAlignment.CENTER);
        document.add(titulo);
        document.add(new Paragraph("\n"));

        Table table = new Table(9);

        table.addCell("PESSOA FISICA/JURIDICA");
        table.addCell("NOME");
        table.addCell("DATA DE CADASTRO");
        table.addCell("CPF/CNPJ");
        table.addCell("RG");
        table.addCell("TELEFONE");
        table.addCell("CELULAR");
        table.addCell("NATURALIDADE");
        table.addCell("ENDEREÇO");

        this.clienteRepository.findAll()
                .stream()
                .sorted(Comparator
                        .comparing((Cliente clientes) -> clientes.getNome())
                        .thenComparing(Cliente::getNome))
                .forEach(cliente -> {
                    table.addCell(cliente.getCpfCnpj());
                    table.addCell(cliente.getNome());
                    table.addCell(DateUtils.format(cliente.getDataCadastro(), "dd-MM-yyyy"));
                    table.addCell(cliente.getCpfCnpj());
                    table.addCell(cliente.getRg());
                    table.addCell(cliente.getTelefone1());
                    table.addCell(cliente.getTelefone2());
                    table.addCell(cliente.getNaturalidade());
                    table.addCell(cliente.getEndereco().getRua());
                });
        document.add(table);
        document.close();
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
