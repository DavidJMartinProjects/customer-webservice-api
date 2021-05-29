package com.customer.exception.handler;

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
    private String timestamp;
}