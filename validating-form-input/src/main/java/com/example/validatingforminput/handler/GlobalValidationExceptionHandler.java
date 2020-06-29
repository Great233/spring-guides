package com.example.validatingforminput.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Great
 */
@ControllerAdvice(basePackages = {"com.example.validatingforminput.controller"})
public class GlobalValidationExceptionHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(GlobalValidationExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validationExceptionHandler(Exception exception) {
        LOGGER.info("catch validator exception");
        BindingResult bindingResult = ((BindException) exception).getBindingResult();

        if (bindingResult.hasErrors()) {
            LOGGER.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
            System.out.println(bindingResult.getTarget());
            return "index";
        }
        return null;
    }
}
