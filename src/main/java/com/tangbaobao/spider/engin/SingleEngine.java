package com.tangbaobao.spider.engin;

import com.tangbaobao.spider.domain.ParseResult;
import com.tangbaobao.spider.domain.Request;
import com.tangbaobao.spider.persist.ItemSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.tangbaobao.spider.engin.DetectionUrl.isVisited;
import static com.tangbaobao.spider.worker.Worker.worker;

/**
 * @author tangxuejun
 * 单机版爬虫引擎：
 * 采用队列实现：递归请求：
 * 城市列表-->城市--->用户列表-->用户详细信息
 * @version 2019-03-19 11:21
 */
@Service
public class SingleEngine {
    private final ItemSaver itemSaver;
    private static final int NUM_ZERO = 0;
    private static final int NUM_FIRST = 1;

    @Autowired
    public SingleEngine(ItemSaver itemSaver) {
        this.itemSaver = itemSaver;
    }

    public void run(Request seeds) {
        List<Request> list = new ArrayList<>();

        list.add(seeds);
        while (list.size() > NUM_ZERO) {
            Request request = list.get(NUM_ZERO);
            list = list.subList(NUM_FIRST, list.size());
            ParseResult result = worker(request);
            List<Request> finalList = list;
            //去重
            result.getRequests()
                    .forEach(req -> filterDuplicate(finalList, req));
            result.getItemList()
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(itemSaver::saveItem);

        }
    }

    public static void filterDuplicate(List<Request> list, Request request) {
        if (!isVisited(request)) {
            list.add(request);
        }
    }


}
