package com.levy.solr.search;

import com.levy.solr.search.domain.BookResponseItem;
import com.levy.solr.search.domain.ResponseItem;
import com.levy.solr.query.QueryBuilder;
import lombok.extern.java.Log;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("bookSearcher")
@Log
public class BookSearcher implements SolrSearcher {

    @Autowired
    private SolrClient client;

    @Override
    public List<? extends ResponseItem> search(QueryBuilder queryBuilder, Map<String, String> parameters) throws IOException, SolrServerException {
        log.info("start search book");
        SolrQuery query = queryBuilder.buildQuery(parameters);
        QueryResponse response = client.query(query);
        List<BookResponseItem> items = response.getBeans(BookResponseItem.class);
        return items;
    }

    public List<ResponseItem> mockSearch(QueryBuilder query, Map<String, String> parameters) throws IOException {
        log.info("start search book");
        List<ResponseItem> list = new ArrayList<>();
        BookResponseItem item = new BookResponseItem();
        item.setId("1");
        item.setName("book1");
        list.add(item);
        return list;
    }
}
