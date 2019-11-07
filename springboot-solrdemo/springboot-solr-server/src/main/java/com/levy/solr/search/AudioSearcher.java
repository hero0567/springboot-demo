package com.levy.solr.search;

import com.levy.solr.search.domain.ResponseItem;
import com.levy.solr.query.QueryBuilder;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component("audioSearcher")
@Log
public class AudioSearcher implements SolrSearcher {
    @Override
    public List<? extends ResponseItem> search(QueryBuilder query, Map<String, String> parameters) throws IOException {
        log.info("start search audio");
        return null;
    }
}
