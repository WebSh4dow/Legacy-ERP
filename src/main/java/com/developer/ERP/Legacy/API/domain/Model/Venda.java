package com.developer.ERP.Legacy.API.domain.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataVenda;
    private BigDecimal precoTotalVenda;

}
