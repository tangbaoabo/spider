package com.tangbaobao.spider.domain;

import com.tangbaobao.spider.funcation.ParseFunc;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tangxuejun
 * 请求实体类
 * @version 2019-03-18 16:36
 */
@Getter
@Setter
@ToString
public class Request {
    //请求url
    public String url;
    //请求方法
    public ParseFunc parseFunc;
}
