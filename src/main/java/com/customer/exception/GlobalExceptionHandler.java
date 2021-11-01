package com.customer.exception;

import static org.springframework.web.client.HttpServerErrorException.InternalServerError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.customer.exception.exceptions.CustomerServiceException;
import com.customer.exception.exceptions.RequestValidationException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorData handleDataAccessException(HttpServletRequest request, DataAccessException ex) {
        log.info("handling DataAccessException: {}.", ex.getMessage());
        return buildErrorData("encountered exception.", ex.getMessage(), request);
    }

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorData handleInternalServerError(HttpServletRequest request, InternalServerError ex) {
        log.info("handling InternalServerError: {}.", ex.getMessage());
        return buildErrorData("encountered exception.", ex.getMessage(), request);
    }

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

    private ErrorData buildErrorData(String errorCode, String message, HttpServletRequest request) {
        return ErrorData.builder()
            .errorCode(errorCode)
            .message(message)
            .url(request.getMethod() + " request to : " + request.getRequestURI())
            .timestamp(LocalDateTime.now().toString())
            .build();
    }

}
