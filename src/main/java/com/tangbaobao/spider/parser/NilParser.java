package com.tangbaobao.spider.parser;

import com.tangbaobao.spider.domain.ParseResult;

import java.util.Map;

/**
 * @author tangxuejun
 * @version 2019-03-18 18:59
 */
public class NilParser {
    public static ParseResult parseFunc(String contents, Map<String,String> url) {
        return new ParseResult();
    }
}
