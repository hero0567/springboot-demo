package com.levy.solr.query;

import org.apache.solr.client.solrj.SolrQuery;

import java.util.Map;

public interface QueryBuilder {

    public SolrQuery buildQuery(Map<String, String> parameters);

}
