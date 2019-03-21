package com.tangbaobao.spider.parser;

import com.tangbaobao.spider.domain.Item;
import com.tangbaobao.spider.domain.ParseResult;
import com.tangbaobao.spider.domain.Profile;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangxuejun
 * @version 2019-03-18 21:08
 */
public class UserParser {
    //抽取用户信息的正则表达式
    private static Pattern ageRe = Pattern.compile("<div class=\"m-btn purple\" data-v-bff6f798>([0-9]+)岁</div>");
    private static Pattern marriageRe = Pattern.compile("<div class=\"m-btn purple\" data-v-bff6f798>([^<][丧偶|离异|未婚]?)</div>");
    private static Pattern xinZuoRe = Pattern.compile("<div class=\"m-btn purple\" data-v-bff6f798>(.{3}\\([0-9]{1,2}\\.[0-9]{1,2}-[0-9]{1,2}\\.[0-9]{1,2}\\))</div>");
    private static Pattern heightRe = Pattern.compile("<div class=\"m-btn purple\" data-v-bff6f798>([0-9]{2,3})cm</div>");
    private static Pattern weightRe = Pattern.compile("<div class=\"m-btn purple\" data-v-bff6f798>([0-9]{2,3})kg</div>");
    private static Pattern workPlaceRe = Pattern.compile("<div class=\"m-btn purple\" data-v-bff6f798>工作地:([^<]+)</div>");
    private static Pattern inComeRe = Pattern.compile("<div class=\"m-btn purple\" data-v-bff6f798>月收入:([^<]+)</div>");
    private static Pattern nameRe = Pattern.compile("<h1 class=\"nickName\" data-v-5b109fc3>([^<]+)</h1>");
    private static Pattern hoKouRe = Pattern.compile("<div class=\"m-btn pink\" data-v-bff6f798>籍贯:([^<]+)</div>");
    private static Pattern carRe = Pattern.compile("<div class=\"m-btn pink\" data-v-bff6f798>([^<]+房)</div>");
    private static Pattern houseRe = Pattern.compile("<div class=\"m-btn pink\" data-v-bff6f798>(.{2}车)</div>");
    private static Pattern idRe = Pattern.compile("http://album\\.zhenai\\.com/u/([\\d]+)[\\s]*");
    private static Pattern photoRe = Pattern.compile("(https://photo\\.zastatic\\.com/images/photo/[^\\.]+\\.jpg)");

    private static Pattern genderRe = Pattern.compile("\"genderString\":\"(.)士\"");


    //正则匹配的位置
    private static final int START_INDEX_GROUP = 1;
    //ES--TYE
    private static final String ZHEN_AI_TYPE = "zhenai";

    public static ParseResult parseUser(String contents, Map<String, String> extraMap) {
        ParseResult parseResult = new ParseResult();
        Profile profile = constructItem(contents);
        String url = StringUtils.trimToEmpty(extraMap.get("url"));
        Item item = new Item(
                url,
                extraUserInfo(url, idRe),
                ZHEN_AI_TYPE,
                profile
        );

        parseResult.setItemList(List.of(item));
        return parseResult;
    }

    private static Profile constructItem(String contents) {
        Profile profile = new Profile();
        profile.setAge(Integer.parseInt((extraUserInfo(contents, ageRe))));
        profile.setCar(extraUserInfo(contents, carRe));
        profile.setHeight(Integer.parseInt(extraUserInfo(contents, heightRe)));
        profile.setWeight(Integer.parseInt(extraUserInfo(contents, weightRe)));
        profile.setHokou(extraUserInfo(contents, hoKouRe));
        profile.setName(extraUserInfo(contents, nameRe));
        profile.setHouse(extraUserInfo(contents, houseRe));
        profile.setMarriage(extraUserInfo(contents, marriageRe));
        profile.setXinzuo(extraUserInfo(contents, xinZuoRe));
        profile.setWorkplace(extraUserInfo(contents, workPlaceRe));
        profile.setIncome(extraUserInfo(contents, inComeRe));
        profile.setPhoto(extraUserInfo(contents,photoRe));
        profile.setGender(extraUserInfo(contents,genderRe));
        return profile;

    }

    private static String extraUserInfo(String content, Pattern pattern) {
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(START_INDEX_GROUP);
        }
        return "0";
    }
}
