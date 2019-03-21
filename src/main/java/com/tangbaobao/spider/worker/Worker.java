package com.tangbaobao.spider.worker;

import com.tangbaobao.spider.domain.ParseResult;
import com.tangbaobao.spider.domain.Request;
import com.tangbaobao.spider.fetcher.Fetcher;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tangxuejun
 * 将请求转换为结果
 * @version 2019-03-19 11:23
 */
@Slf4j
public class Worker {
    public static ParseResult worker(Request request) {
        try {
            Fetcher fetcher = new Fetcher();
            String content = fetcher.fetcher(request.getUrl());
            return request.getParseFunc().parseFunc(content, request.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ParseResult();
    }
}
