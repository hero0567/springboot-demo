package com.levy.solr.filter;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.icu.segmentation.ICUTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class LowerCaseFilterFactoryTest {

    @Test
    public void testLowerCaseFilterFactory() throws IOException {

        Reader reader = new StringReader("This is a TEST string");
        ICUTokenizer icut = new ICUTokenizer();
        icut.setReader(reader);

        Map<String, String> map = new HashMap<>();
        LowerCaseFilterFactory lowerCaseFactory = new LowerCaseFilterFactory(map);
        TokenStream tokenStream = lowerCaseFactory.create(icut);

        icut.reset();
        while (tokenStream.incrementToken()) {
            System.out.println(icut.toString());
            System.out.println(icut.getAttribute(CharTermAttribute.class));
        }
        icut.close();
    }
}
