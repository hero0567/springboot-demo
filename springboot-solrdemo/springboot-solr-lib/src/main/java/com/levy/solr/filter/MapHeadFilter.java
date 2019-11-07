package com.levy.solr.filter;

import lombok.extern.java.Log;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.util.CharacterUtils;
import org.apache.lucene.util.ToStringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log
public class MapHeadFilter extends TokenFilter {

    private final CharacterUtils charUtils = CharacterUtils.getInstance();
    private final CharTermAttribute termAtt = (CharTermAttribute) this.addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);

    private Map<String, String> map = new HashMap<>();
    private char[] originTermBuffer;
    private int originLen;

    public MapHeadFilter(TokenStream in, Map<String, String> map) {
        super(in);
        this.map = map;
    }

    public final boolean incrementToken() throws IOException {

        if (originTermBuffer == null) {
            if (this.input.incrementToken()) {
                char[] bufferToken = termAtt.buffer(); //length is 16 if the char[] is less than 16
                char[] token = Arrays.copyOf(bufferToken, termAtt.length());
                String v = map.get(String.valueOf(token));
                if (v != null) {
                    originTermBuffer = termAtt.buffer().clone();
                    originLen = termAtt.length();
                    termAtt.copyBuffer(v.toCharArray(), 0, v.length());
                }
                return true;
            }
        } else {
            clearAttributes();
            termAtt.copyBuffer(originTermBuffer, 0, originLen);
            originTermBuffer = null;
            return true;
        }

        return false;
    }
}
