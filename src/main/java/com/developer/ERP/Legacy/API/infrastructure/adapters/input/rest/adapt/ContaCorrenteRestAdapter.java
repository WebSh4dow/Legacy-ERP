package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.adapt;

import com.developer.ERP.Legacy.API.application.ports.input.CreateContaCorrenteUseCase;
import com.developer.ERP.Legacy.API.application.ports.input.GetContaCorrenteByCodigoUseCase;
import com.developer.ERP.Legacy.API.application.ports.input.GetContasCorrenteUseCase;
import com.developer.ERP.Legacy.API.application.ports.input.UpdateContaCorrenteUseCase;
import com.developer.ERP.Legacy.API.domain.model.ContaCorrente;
import com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.request.ContaCorrenteRequest;
import com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response.ContaCorrenteResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ContaCorrenteRestAdapter {

    private final GetContasCorrenteUseCase getContasCorrenteUseCase;

    private final GetContaCorrenteByCodigoUseCase getContaCorrenteByCodigoUseCase;

    private final CreateContaCorrenteUseCase createContaCorrenteUseCase;

    private final UpdateContaCorrenteUseCase updateContaCorrenteUseCase;

    private final ModelMapper mapper;

    @PostMapping(value = "/contas-correntes/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContaCorrenteResponse> createContaCorrente(@RequestBody ContaCorrenteRequest contaCorrenteRequest) {
        ContaCorrente contaCorrente = mapper.map(contaCorrenteRequest, ContaCorrente.class);
        contaCorrente = createContaCorrenteUseCase.createContaBancaria(contaCorrente);

        return new ResponseEntity<>(mapper.map(contaCorrente, ContaCorrenteResponse.class), HttpStatus.CREATED);
    }

    @PutMapping(value = "/contas-correntes/editar/{codigo}")
    public ResponseEntity<ContaCorrenteResponse> updateContaCorrente(@PathVariable Long codigo, @RequestBody ContaCorrenteRequest contaCorrenteRequest) {
        ContaCorrente contaCorrente = mapper.map(contaCorrenteRequest, ContaCorrente.class);

        contaCorrente.setCodigo(codigo);
        contaCorrente = updateContaCorrenteUseCase.updateContaBancaria(contaCorrente);

        return new ResponseEntity<>(mapper.map(contaCorrente, ContaCorrenteResponse.class), HttpStatus.OK);
    }

    @GetMapping(value = "/contas-correntes/{codigo}")
    public ResponseEntity<ContaCorrenteResponse> getContaCorrenteByCodigo(@PathVariable Long codigo) {
        ContaCorrente contaCorrente = getContaCorrenteByCodigoUseCase.getContaCorrenteByCodigo(codigo);

        return new ResponseEntity<>(mapper.map(contaCorrente, ContaCorrenteResponse.class), HttpStatus.OK);
    }

    @GetMapping(value = "/contas-correntes")
    public ResponseEntity<List<ContaCorrenteResponse>> getContasCorrentes() {
        List<ContaCorrente> contasCorrentes = getContasCorrenteUseCase.getContasCorrentes();
        List<ContaCorrenteResponse> responses = new ArrayList<>();

        for (ContaCorrente contaCorrente : contasCorrentes) {
            responses.add(mapper.map(contaCorrente, ContaCorrenteResponse.class));
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
