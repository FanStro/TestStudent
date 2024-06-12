package com.itheima.validation;

import com.itheima.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @program: big-event
 * @description: StateValidation
 * @author: 樊福蕰
 * @create: 2024-05-21 16:08
 **/
public class StateValidation implements ConstraintValidator<State,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null)
            return false;
        if(value.equals("已发布")||value.equals("草稿")){
            return true;
        }
        return false;
    }
}
