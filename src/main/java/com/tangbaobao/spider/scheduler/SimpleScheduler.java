package com.tangbaobao.spider.scheduler;


import com.tangbaobao.spider.domain.Request;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author tangxuejun
 * 基于{@link ArrayBlockingQueue}实现的简单任务调度器
 * @version 2019-03-19 12:20
 */
public class SimpleScheduler implements Scheduler {
    private Queue<Request> requestQueue;

    @Override
    public Queue<Request> workQueue() {
        return requestQueue;
    }

    @Override
    public void run() {
        requestQueue = new LinkedList<>();
    }

    @Override
    public void submit(Request request) {
        requestQueue.add(request);
    }


}
