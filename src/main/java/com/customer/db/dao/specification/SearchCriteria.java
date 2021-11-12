package com.customer.db.dao.specification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperation operation;

}
