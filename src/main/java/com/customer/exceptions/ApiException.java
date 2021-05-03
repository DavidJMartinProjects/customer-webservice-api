package com.customer.exceptions;

import lombok.Builder;
import lombok.Data;

/**
 * @author davidjmartin
 */
@Data
@Builder
public class ApiException {

    private String url;
    private String errorCode;
    private String message;
    private String timestamp;

}