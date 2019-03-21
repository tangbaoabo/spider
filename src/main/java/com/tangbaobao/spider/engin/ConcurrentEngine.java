package com.tangbaobao.spider.engin;

import com.tangbaobao.spider.domain.ParseResult;
import com.tangbaobao.spider.domain.Request;
import com.tangbaobao.spider.scheduler.Scheduler;
import com.tangbaobao.spider.worker.Worker;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangxuejun
 * 并发版爬虫引擎
 * @version 2019-03-19 12:17
 */
@Setter
@Getter
public class ConcurrentEngine {

    public static final int MAX_SIZE = 20;
    public static LinkedList<Request> reqList = new LinkedList<>();
    public static LinkedList<ParseResult> respList = new LinkedList<>();
    public static Lock lock = new ReentrantLock();
    //仓库满的条件变量
    public static Condition full = lock.newCondition();
    //仓库空的条件变量
    public static Condition empty = lock.newCondition();


    //调度器
    private Scheduler scheduler;
    //worker数量
    private int workCount;

    public void run(Request... requests) throws InterruptedException {
        scheduler.run();

        reqList.addAll(Arrays.asList(requests));

            while (true) {
                if (!reqList.isEmpty()) {
                    respList.add(Worker.worker(reqList.remove()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!respList.isEmpty()) {
                    for (ParseResult parseResult : respList) {
                        reqList.addAll(parseResult.getRequests());
                        System.out.println(parseResult.getItemList());
                    }
                }
        }

    }


}
