package com.tangbaobao.spider.parser;

import com.tangbaobao.spider.domain.ParseResult;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author tangxuejun
 * 获取城市列表转换器
 * @version 2019-03-18 16:53
 */
@Slf4j
public class CityListParser {
    //提取城市URL正则
    private static Pattern cityRe = Pattern.compile("<a[\\s]+href=\"(http://www.zhenai.com/zhenghun/[a-z0-9]+)\"[^>]*>([^<]+)</a>");

    public ParseResult parseCityList(String content) {
        ParseResult parseResult = new ParseResult();
        Matcher matcher = cityRe.matcher(content);
        Stream<MatchResult> results = matcher.results();
        parseResult.setRequests(ConvertUtil.constructRequestList(
                results,
                (contents, url) -> CityParser.parseCity(contents)
        ));
        return parseResult;
    }


}
