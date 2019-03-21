package com.tangbaobao.spider.engin;

import com.tangbaobao.spider.domain.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tangxuejun
 * 检查URL是否重复
 * @version 2019-03-19 11:27
 */
public class DetectionUrl {
    //TODO 此处的map会随着爬虫时间的增长呈线性增长,待优化
    private static Map<String, Boolean> map = new HashMap<>();

    static boolean isVisited(Request request) {
        if (map.containsKey(request.getUrl())) {
            return true;
        }
        map.put(request.getUrl(), true);
        return false;
    }
}
