package com.developer.ERP.Legacy.API.core.annotations;

import com.developer.ERP.Legacy.API.core.validation.validators.impl.CnpjValidatorImpl;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {CnpjValidatorImpl.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Cnpj {
    String message() default "O CNPJ informado não é valido, porfavor informe um cnpj valido!";
    Class <?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
