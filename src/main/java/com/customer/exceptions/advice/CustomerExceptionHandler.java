package com.customer.exceptions.advice;


import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.customer.exceptions.ErrorData;
import com.customer.exceptions.ResourceNotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@ControllerAdvice
@Slf4j
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorData handleDataAccessException(@NonNull HttpServletRequest request, @NonNull EmptyResultDataAccessException ex) {
        log.info("handling EmptyResultDataAccessException: {}.", ex.getMessage());
        return ErrorData.builder()
            .errorCode("INTERNAL_ERROR")
            .message(ex.getMessage())
            .url(request.getRequestURI())
            .timestamp(LocalDateTime.now().toString())
            .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorData handleEntityNotFound(@NonNull HttpServletRequest request, @NonNull ResourceNotFoundException ex) {
        log.info("handling EntityNotFoundException: {}.", ex.getMessage());
        return ErrorData.builder()
            .errorCode("NOT_FOUND")
            .message(ex.getMessage())
            .url(request.getRequestURI())
            .timestamp(LocalDateTime.now().toString())
            .build();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorData handleNullPointerException(@NonNull NullPointerException ex) {
        log.info("handling NullPointerException: {}.", ex.getMessage());
        return ErrorData.builder()
            .errorCode("BAD_REQUEST")
            .message(ex.getCause().getMessage())
            .timestamp(LocalDateTime.now().toString())
            .build();
    }

}