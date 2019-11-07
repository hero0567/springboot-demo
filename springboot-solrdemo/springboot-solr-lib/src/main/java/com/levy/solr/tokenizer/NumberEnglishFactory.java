package com.levy.solr.tokenizer;

import com.levy.solr.filter.NumberEnglishFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.util.TokenFilterFactory;
import org.apache.lucene.util.Version;

import java.util.Map;

public class NumberEnglishFactory extends TokenFilterFactory {

    public NumberEnglishFactory(Map<String, String> args) {
        super(args);
    }

    @Override
    public NumberEnglishFilter create(TokenStream tokenStream) {
        return new NumberEnglishFilter(tokenStream);
    }
}
