package com.customer.exceptions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

/**
 * @author davidjmartin
 */
@Value
@Builder
public class ErrorData {

    private String url;
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;

}