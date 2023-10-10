package com.developer.ERP.Legacy.API.core.annotations;

import com.developer.ERP.Legacy.API.core.validation.validators.impl.cpf.CpfValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {CpfValidatorImpl.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Cpf {
    String message() default "O CPF informado não é valido, porfavor informe um cpf valido!";
    Class <?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}