// validation/FieldMatchValidator.java
package com.udb.proyectofinalaerolinea.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String first;
    private String second;

    @Override
    public void initialize(FieldMatch constraint) {
        this.first = constraint.first();
        this.second = constraint.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        var wrapper = new BeanWrapperImpl(value);
        Object f = wrapper.getPropertyValue(first);
        Object s = wrapper.getPropertyValue(second);
        return f == null && s == null || (f != null && f.equals(s));
    }
}
