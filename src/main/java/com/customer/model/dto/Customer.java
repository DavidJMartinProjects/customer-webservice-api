package com.customer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author davidjmartin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String address;
    private String city;
    private String country;

    @NonNull
    private String email;

}
