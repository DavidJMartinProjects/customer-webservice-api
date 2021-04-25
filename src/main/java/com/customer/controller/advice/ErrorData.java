package com.customer.controller.advice;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

/**
 * @author davidjmartin
 */
@Data
@Builder
public class ErrorData {

    private String url;
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;

}