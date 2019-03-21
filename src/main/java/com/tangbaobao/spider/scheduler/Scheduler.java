package com.tangbaobao.spider.scheduler;


import com.tangbaobao.spider.domain.Request;

import java.util.Queue;

/**
 * @author tangxuejun
 * @version 2019-03-19 12:22
 */
public interface Scheduler {
    void submit(Request request);

    Queue<Request> workQueue();

    void run();
}
