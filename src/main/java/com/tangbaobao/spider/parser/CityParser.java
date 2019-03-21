package com.tangbaobao.spider.parser;

import com.tangbaobao.spider.domain.ParseResult;
import com.tangbaobao.spider.domain.Request;
import com.tangbaobao.spider.funcation.ParseFunc;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tangbaobao.spider.parser.ConvertUtil.constructRequestList;

/**
 * @author tangxuejun
 * @version 2019-03-18 18:52
 */
@Slf4j
public class CityParser {

    private static Pattern userRe = Pattern.compile("<a href=\"(http://album.zhenai.com/u/[0-9]+)\"[^>]*>([^<]+)</a>");

    private static Pattern genderRe = Pattern.compile("<td width=\"180\"><span class=\"grayL\">性别：</span>([^<]+)</td>");

    private static Pattern cityRe = Pattern.compile("href=\"(http://www.zhenai.com/zhenghun/[^\"]+)\"");


    public static ParseResult parseCity(String contents) {

        ParseResult parseResult = new ParseResult();
        //提取用户
        Matcher userMatch = userRe.matcher(contents);


        List<Request> requestList = constructRequestList(
                userMatch.results(),
                CityParser.parseFunc()
        );
        //提取用户列表中的城市
        Matcher cityMatch = cityRe.matcher(contents);
        requestList.addAll(constructRequestList(
                cityMatch.results(),
                (contents1, url) -> parseCity(contents1))
        );

        parseResult.setRequests(requestList);
        return parseResult;
    }

    private static ParseFunc parseFunc() {
        return (contents, url) -> UserParser.parseUser(contents, Map.of(
                "url", url
        ));
    }
}
