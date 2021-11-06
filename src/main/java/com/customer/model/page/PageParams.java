package com.customer.model.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dave
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {

    private int pageNumber;
    private int pageSize;
    private String sortKey;
    private String sortDirection;

}
