package com.tangbaobao.spider.funcation;

import com.tangbaobao.spider.domain.ParseResult;

/**
 * @author tangxuejun
 * @version 2019-03-18 16:49
 */
@FunctionalInterface
public interface ParseFunc {
    /**
     * 转换函数接口
     *
     * @param contents 需要转换的内容
     * @param url      下一个请求的URL
     * @return 返回转换之后的结果 {@link ParseResult}
     */
    ParseResult parseFunc(String contents, String url);
}
