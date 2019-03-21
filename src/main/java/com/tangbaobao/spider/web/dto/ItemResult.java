package com.tangbaobao.spider.web.dto;

import com.tangbaobao.spider.domain.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-03-20 18:56
 */
@Getter
@Setter
@ToString
public class ItemResult {
    private int count;
    private List<Item> itemList;
    private String query;
    private int start;
    private int from;
    private int pre;
    private int next;
}
