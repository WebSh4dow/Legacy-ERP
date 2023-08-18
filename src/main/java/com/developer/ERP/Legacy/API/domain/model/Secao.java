package com.developer.ERP.Legacy.API.domain.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Secao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ElementCollection
    @CollectionTable(name = "secao_setores",
            joinColumns = @JoinColumn(name = "secao_id"))
    private List <Setor>setores;

}
