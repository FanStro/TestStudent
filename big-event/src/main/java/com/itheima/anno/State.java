package com.itheima.anno;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {StateValidation.class})
@Target({ FIELD})
@Retention(RUNTIME)

public @interface State {
    String message() default "state参数的值只能是已发布或者草稿";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
