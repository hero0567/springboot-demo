package com.levy.solr.search;

import com.levy.solr.search.domain.ResponseItem;
import com.levy.solr.query.QueryBuilder;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SolrSearcher {
    List<? extends ResponseItem> search(QueryBuilder query, Map<String, String> parameters) throws IOException, SolrServerException;
}
