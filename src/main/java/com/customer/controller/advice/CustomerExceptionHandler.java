package com.customer.controller.advice;


import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.customer.exceptions.EntityNotFoundException;
import com.customer.exceptions.ErrorData;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@Slf4j
@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ErrorData handleEntityNotFound(@NonNull HttpServletRequest request, @NonNull EntityNotFoundException ex) {
        log.info("handling EntityNotFoundException: {}.", ex.getMessage());
        return ErrorData.builder()
			.errorCode("NOT_FOUND")
			.message(ex.getMessage())
			.url(request.getRequestURI())
			.timestamp(LocalDateTime.now())
			.build();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ErrorData handleNullPointer(@NonNull NullPointerException ex) {

        return ErrorData.builder()
			.errorCode("BAD_REQUEST")
			.message(ex.getMessage())
			.timestamp(LocalDateTime.now())
			.build();
    }

}