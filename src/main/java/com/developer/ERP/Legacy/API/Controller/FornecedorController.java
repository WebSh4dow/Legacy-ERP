package com.developer.ERP.Legacy.API.Controller;

import com.developer.ERP.Legacy.API.Service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/fornecedores")
public class FornecedorController {
    @Resource
    private FornecedorService fornecedorService;
}
