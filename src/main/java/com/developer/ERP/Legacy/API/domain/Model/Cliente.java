package com.developer.ERP.Legacy.API.domain.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dataNascimento;
   
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataCadastro = LocalDate.now();
    
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
    @ManyToOne
    @JoinColumn(name = "pessoaJuridica_id")
    private PessoaJuridica pessoaJuridica;


}
