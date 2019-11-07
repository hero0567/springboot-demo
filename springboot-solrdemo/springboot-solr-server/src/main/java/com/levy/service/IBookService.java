package com.levy.service;

import com.levy.solr.search.domain.ResponseItem;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IBookService {

    public List<ResponseItem> search(String domain, Map<String, String> parameters) throws IOException, SolrServerException;
}
