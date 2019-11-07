package com.levy.solr.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SolrSearcherFactory {

    @Autowired
    private Map<String, SolrSearcher> searchers;

    public SolrSearcher getSearcher(String domain){
        return searchers.get(domain + "Searcher");
    }
}
