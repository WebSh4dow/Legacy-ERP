package com.developer.ERP.Legacy.API.core.annotations;

import com.developer.ERP.Legacy.API.core.validation.validators.impl.validatorFactory.ConstraintValidatorImpl;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy={ConstraintValidatorImpl.class})
@Target({ElementType.TYPE})
@Retention(RUNTIME)
public @interface UniqueKeyValidator {
    String [] nomePropiedades();
    String message() default "Registro único ocorreu violação de dados!";
    Class<?>[] groups() default {};
    Class < ? extends Payload[]> [] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        UniqueKeyValidator[] value();
    }


}
