package com.tangbaobao.spider.parser;

import com.tangbaobao.spider.domain.Request;
import com.tangbaobao.spider.funcation.ParseFunc;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author tangxuejun
 * 转换工具
 * @version 2019-03-19 09:51
 */
@Slf4j
class ConvertUtil {
    static List<Request> constructRequestList(Stream<MatchResult> resultStream, ParseFunc parseFunc) {
        return resultStream
                .map(x -> x.group(1))
                .map(url -> convertFunc(url, parseFunc))
                .collect(toList());
    }

   public   static Request convertFunc(String url, ParseFunc parseFunc) {
        Request request = new Request();
        log.info(url);
        request.setUrl(url);
        request.setParseFunc(parseFunc);
        return request;
    }
}
