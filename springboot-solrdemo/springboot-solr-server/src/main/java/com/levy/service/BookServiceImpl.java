package com.levy.service;

import com.levy.solr.search.SolrSearcher;
import com.levy.solr.search.SolrSearcherFactory;
import com.levy.solr.search.domain.ResponseItem;
import com.levy.solr.query.QueryBuilder;
import com.levy.solr.query.QueryBuilderFactory;
import lombok.extern.java.Log;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component("bookServiceImpl")
@Log
public class BookServiceImpl implements IBookService {

    @Autowired
    private SolrSearcherFactory solrSearcherFactory;
    @Autowired
    private QueryBuilderFactory queryBuilderFactory;

    @Autowired
    private Map<String, SolrSearcher> searchers;

    public List<ResponseItem> search(String domain, Map<String, String> parameters) throws IOException, SolrServerException {
        log.info("Try to find searcher by " + domain);
        SolrSearcher solrSearcher = solrSearcherFactory.getSearcher(domain);
        QueryBuilder queryBuilder = queryBuilderFactory.getQueryBuilder(domain);
        List<ResponseItem> responseItems = (List<ResponseItem>) solrSearcher.search(queryBuilder, parameters);
        return responseItems;
    }
}
