package com.tangbaobao.spider.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangxuejun
 * 解析结果实体类
 * @version 2019-03-18 16:37
 */
@Getter
@Setter
@ToString
public class ParseResult {
    List<Request> requests = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();
}
