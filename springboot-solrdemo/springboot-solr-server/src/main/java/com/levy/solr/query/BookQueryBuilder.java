package com.levy.solr.query;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("bookQueryBuilder")
@Log
public class BookQueryBuilder implements QueryBuilder {
    @Override
    public SolrQuery buildQuery(Map<String, String> parameters) {
        SolrQuery query = new SolrQuery();
        String id = parameters.get("id");

        StringBuilder q = new StringBuilder();
        if (StringUtils.isEmpty(id)){
            q.append("*:*");
        }else{
            q.append("id:").append(id);
        }
//        query.setFields("banmaPoiName");
//        query.setHighlightSimplePre("{");
//        query.setHighlightSimplePost("}");
//        query.setHighlight(true);
//        query.setHighlightRequireFieldMatch(true);
//        query.addHighlightField("banmaPoiName");
        query.setRows(10);
        query.setQuery(q.toString());
        log.info("Build solr query:" + query.toString());
        return query;
    }
}
