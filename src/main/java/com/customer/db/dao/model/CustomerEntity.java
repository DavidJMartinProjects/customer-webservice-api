package com.customer.db.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author davidjmartin
 */
@Entity
@Table(name = "customers")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "please provide a firstName")
    private String firstName;

    @NotEmpty(message = "please provide a lastName")
    private String lastName;

    private String address;
    private String city;
    private String country;

    @NotEmpty(message = "please provide an email")
    private String email;

    private String image;

}
