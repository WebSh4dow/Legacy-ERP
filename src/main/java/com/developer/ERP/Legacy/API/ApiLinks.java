package com.developer.ERP.Legacy.API;

import com.developer.ERP.Legacy.API.api.v1.controller.ClienteController;
import org.springframework.hateoas.*;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ApiLinks {
	
	public static final TemplateVariables VARIAVEIS_DE_PAGINACAO = new TemplateVariables(
			new TemplateVariable("page",VariableType.REQUEST_PARAM),
			new TemplateVariable("size",VariableType.REQUEST_PARAM),
			new TemplateVariable("sort",VariableType.REQUEST_PARAM));


	public static final TemplateVariables VARIAVEIS_DE_PROJECOES = new TemplateVariables(
			new TemplateVariable("projecao",VariableType.REQUEST_PARAM));


				
}
