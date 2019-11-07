package com.levy.solr.query;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("audioQueryBuilder")
public class AudioQueryBuilder implements QueryBuilder {
    @Override
    public SolrQuery buildQuery(Map<String, String> parameters) {
        SolrQuery solrQuery = new SolrQuery();
        return solrQuery;
    }
}
