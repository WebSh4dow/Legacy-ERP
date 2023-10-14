package com.developer.ERP.Legacy.API.domain.model;

import lombok.Data;
import javax.persistence.*;
import com.developer.ERP.Legacy.API.domain.enumerated.Estado;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String cep;
    
    private String bairro;
    
    private String rua;
    
    private Integer numero;
    
    private String proximidades;
    
    private String cidade;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;
    


}
