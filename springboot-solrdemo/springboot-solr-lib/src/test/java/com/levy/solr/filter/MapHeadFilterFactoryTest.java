package com.levy.solr.filter;

import com.levy.solr.tokenizer.MapHeadFilterFactory;
import com.levy.solr.tokenizer.NumberEnglishFactory;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.icu.segmentation.ICUTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MapHeadFilterFactoryTest {

    @Test
    public void testNumberEnglishFilter() throws IOException {

        Reader reader = new StringReader("This is a TEST a string This");
        ICUTokenizer icut = new ICUTokenizer();
        icut.setReader(reader);

        Map<String, String> map = new HashMap<>();
        //TODO: load map from file
        map.put("This", "These");
        map.put("a", "an");
        MapHeadFilterFactory mapHeadFilterFactory = new MapHeadFilterFactory(map);
        TokenStream tokenStream = mapHeadFilterFactory.create(icut);

        icut.reset();
        while (tokenStream.incrementToken()) {
            System.out.println(icut.toString());
            System.out.println(icut.getAttribute(CharTermAttribute.class));
        }
        icut.close();

    }
}
