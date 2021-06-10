package com.customer.exception.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.customer.exception.CustomerServiceException;
import com.customer.exception.RequestValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorData handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        log.info("handling ValidationFailureException with propagated violation errors: {}.", ex.getMessage());
        List<String> errors = ex.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());

        return buildErrorData("request validation failure.", errors.toString(), request);
    }

    @ExceptionHandler(RequestValidationException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorData handleRequestValidationException(HttpServletRequest request, RequestValidationException ex) {
        log.info("handling RequestValidationException with message: {}.", ex.getMessage());
        return buildErrorData("request validation failure.", ex.getMessage(), request);
    }

    @ExceptionHandler(CustomerServiceException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorData handleEntityNotFound(HttpServletRequest request, CustomerServiceException ex) {
        log.info("handling EntityNotFoundException: {}.", ex.getMessage());
        return buildErrorData("resource not found.", ex.getMessage(), request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorData handleDataAccessException(HttpServletRequest request, EmptyResultDataAccessException ex) {
        log.info("handling EmptyResultDataAccessException: {}.", ex.getMessage());
        return buildErrorData("resource not found.", ex.getMessage(), request);
    }

    private ErrorData buildErrorData(String errorCode, String message, HttpServletRequest request) {
        return ErrorData.builder()
            .errorCode(errorCode)
            .message(message)
            .url(request.getMethod() + " request to : " + request.getRequestURI())
            .timestamp(LocalDateTime.now().toString())
            .build();
    }

}