package com.example.myutil.beanValidator;

import com.example.exception.ResultException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据校验器
 *
 * @Author John
 * @Create 2019/4/15 11:39
 */
public final class BeanValidator {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static  <T> void validate(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> errorMessages = validator.validate(t);
        String message = errorMessages.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        if (StringUtils.isNotBlank(message)){
            throw new ResultException(message);
        }
    }

}
