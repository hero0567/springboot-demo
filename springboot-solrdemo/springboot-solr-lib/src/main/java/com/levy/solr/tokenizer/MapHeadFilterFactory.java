package com.levy.solr.tokenizer;

import com.levy.solr.filter.MapHeadFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import java.util.HashMap;
import java.util.Map;

public class MapHeadFilterFactory extends TokenFilterFactory {

    private Map<String, String> map = new HashMap<>();

    public MapHeadFilterFactory(Map<String, String> args) {
        super(args);
        this.map = args;
    }

    @Override
    public MapHeadFilter create(TokenStream tokenStream) {
        return new MapHeadFilter(tokenStream, map);
    }
}

