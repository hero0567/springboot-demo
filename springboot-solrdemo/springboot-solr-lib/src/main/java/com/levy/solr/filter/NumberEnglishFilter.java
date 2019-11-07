package com.levy.solr.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.java.Log;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.Version;

@Log
public class NumberEnglishFilter extends TokenFilter {
    private char[] curTermBuffer;
    /* char[]总长度 */
    private int curTermLength;
    /* 当前切割处,+1表示下一次切割处 */
    private int curGramSize;
    /* number和english位置 */
    private List<Integer> positions;
    /* 相对位置，即在整个输入词的位置 */
    private int tokStart;
    /* positions所在的位置 */
    private int position;
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    /* 表示开始位置，结束位置 */
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);

    /* private final TypeAttribute typeAtt = addAttribute(TypeAttribute.class); */
    public NumberEnglishFilter(TokenStream input) {
        super(input);
    }

    @Override
    public final boolean incrementToken() throws IOException {
        while (true) {
            /* 表示上一个token结束，进入下一个token */
            if (curTermBuffer == null) {
                if (!input.incrementToken()) {
                    return false;
                } else {
                    curTermBuffer = termAtt.buffer().clone();
                    curTermLength = termAtt.length();
                    /* 放置position list */
                    positions = getPositions(curTermBuffer, curTermLength);
                    tokStart = offsetAtt.startOffset();
                    curGramSize = positions.get(0);
                    position = 0;
                }
            }
            if (curGramSize < curTermLength && curTermLength > 1 && positions.size() > 2) {
                position++;
                offsetAtt.setOffset(tokStart + curGramSize, tokStart + positions.get(position));
                termAtt.copyBuffer(curTermBuffer, curGramSize, positions.get(position) - curGramSize);
                curGramSize = positions.get(position);
                return true;
            } else {
                clearAttributes();
                offsetAtt.setOffset(tokStart + 0, tokStart + curTermLength);
                termAtt.copyBuffer(curTermBuffer, 0, curTermLength);
                curTermBuffer = null;
                return true;
            }
        }
    }

    /* 记录下number和english位置 */
    public List<Integer> getPositions(char[] term, int length) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        for (int i = 0; i < length; ) {
            if (Character.isDigit(term[i])) {
                while (++i < length && Character.isDigit(term[i])) ;
                list.add(i);
                continue;
            } else if (i < length && Character.isLetter(term[i])) {
                while (++i < length && Character.isLetter(term[i])) ;
                list.add(i);
                continue;
            } else {
                list.add(++i);
            }
        }
        if (!Character.isDigit(term[length - 1]) && !Character.isLetter(term[length - 1])) list.add(length);
        return list;
    }
}