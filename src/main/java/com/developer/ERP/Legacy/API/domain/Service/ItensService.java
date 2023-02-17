package com.developer.ERP.Legacy.API.domain.Service;

import com.developer.ERP.Legacy.API.domain.Model.Cliente;
import com.developer.ERP.Legacy.API.domain.Model.Itens;
import com.developer.ERP.Legacy.API.domain.Model.Secao;
import com.developer.ERP.Legacy.API.domain.Repository.ClienteRepository;
import com.developer.ERP.Legacy.API.domain.Repository.ItensRepository;
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
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ItensService {
    @Autowired
    private ItensRepository itensRepository;

    @Transactional
    public ResponseEntity<Itens> salvar(Itens itens){
        Itens objetoItens = itensRepository.save(itens);
        return ResponseEntity.ok().body(objetoItens);
    }
    public List <Itens> todos(){
        return itensRepository.findAll();
    }
    public ResponseEntity <Itens> pesquisarPor(Long id){
        Itens pesquisar = itensRepository.findById(id)
                .orElse(null);
        return ResponseEntity.ok().body(pesquisar);
    }
    @Transactional
    public Itens editarItens(Long id,Itens itens){
        Itens pesquisarId = itensRepository.findById(id)
                .orElse(null);
        if (pesquisarId ==null){
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(itens,pesquisarId,"id");
        return itensRepository.save(pesquisarId);
    }

    @Transactional
    public ResponseEntity<Map<String, Boolean>>remover(Long id){
        Itens pesquisarItens = itensRepository.findById(id)
                .orElse(null);
        itensRepository.delete(pesquisarItens);
        Map <String,Boolean> response = new HashMap<>();
        response.put("Removido",true);
        return ResponseEntity.ok().body(response);
    }

}
