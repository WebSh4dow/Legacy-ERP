package com.developer.ERP.Legacy.API.infrastructure.adapters.output.persistence.entity;

import com.developer.ERP.Legacy.API.domain.enums.UF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name = "dados_pessoais")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String descricao;

    private String cep;

    private String numero;

    private String complemento;

    private String bairro;

    private String celular;

    private String cidade;

    @Enumerated(EnumType.STRING)
    private UF uf;
}
