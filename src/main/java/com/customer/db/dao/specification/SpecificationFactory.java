package com.customer.db.dao.specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.customer.db.dao.specification.criteria.CustomerSpecification;
import com.customer.db.dao.specification.criteria.SearchCriteria;
import com.customer.db.dao.specification.enums.SearchOperation;

/**
 * @author DavidJMartin
 */
public class SpecificationFactory {

    private static final int SEARCH_KEY_INDEX = 1;
    private static final int SEARCH_VALUE_INDEX = 3;

    private static final String KEY_VALUE_PAIR_REGEX = "(\\w+?)(:)(\\w+?),";

    private SpecificationFactory() {}

    public static CustomerSpecification build(String search) {
        CustomerSpecification specification = new CustomerSpecification();
        final Pattern pattern = Pattern.compile(KEY_VALUE_PAIR_REGEX);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            specification.add(
                SearchCriteria.builder()
                    .key(matcher.group(SEARCH_KEY_INDEX))
                    .value(matcher.group(SEARCH_VALUE_INDEX))
                    .operation(SearchOperation.LIKE)
                    .build()
            );
        }
        return specification;
    }

}
