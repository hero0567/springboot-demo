package com.levy.solr.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QueryBuilderFactory {

    @Autowired
    private Map<String, QueryBuilder> queryBuilders;

    public QueryBuilder getQueryBuilder(String domain) {
        return queryBuilders.get(domain + "QueryBuilder");
    }
}
