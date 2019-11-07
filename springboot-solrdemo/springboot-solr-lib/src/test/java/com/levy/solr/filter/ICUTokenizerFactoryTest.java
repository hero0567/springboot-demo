package com.levy.solr.filter;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.icu.segmentation.ICUTokenizer;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class ICUTokenizerFactoryTest {


    @Test
    public void testTokenizer() throws IOException {
        Reader reader = new StringReader("金山城");
        ICUTokenizer icut = new ICUTokenizer();
        icut.setReader(reader);
        icut.reset();
        while (icut.incrementToken()) {
            System.out.println(icut.toString());
            System.out.println(icut.getAttribute(CharTermAttribute.class));
        }
        icut.close();
    }


    public void testSynonymFilterFactory() throws IOException {

        Reader reader = new StringReader("This is a TEST string");
        ICUTokenizer icut = new ICUTokenizer();
        icut.setReader(reader);

        Map<String, String> map = new HashMap<>();
        map.put("expand", "true");
        map.put("ignoreCase", "true");
        map.put("synonyms", "synonyms.txt");

        SynonymFilterFactory synonymFilterFactory = new SynonymFilterFactory(map);
        TokenStream tokenStream = synonymFilterFactory.create(icut);

        icut.reset();
        while (tokenStream.incrementToken()) {
            System.out.println(icut.toString());
            System.out.println(icut.getAttribute(CharTermAttribute.class));
        }
        icut.close();
    }


}
