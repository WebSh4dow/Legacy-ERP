package com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.adapt;

import com.developer.ERP.Legacy.API.application.ports.input.GetHistoricoImoveisProprietarioUseCase;
import com.developer.ERP.Legacy.API.domain.model.Imovel;
import com.developer.ERP.Legacy.API.infrastructure.adapters.input.rest.response.ImovelResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ImovelRestAdapter {

    private final GetHistoricoImoveisProprietarioUseCase historicoImoveisProprietarioUseCase;
    private final ModelMapper mapper;

    @GetMapping("/imoveis/historico-imoveis-proprietario")
    public ResponseEntity<List<ImovelResponse>> getHistoricoImoveisProprietario() {

        List<Imovel> historicoImoveis = historicoImoveisProprietarioUseCase.historicoImoveis();
        List<ImovelResponse> responses = new ArrayList<>();

        for (Imovel imovel : historicoImoveis) {
            responses.add(mapper.map(imovel, ImovelResponse.class));
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
