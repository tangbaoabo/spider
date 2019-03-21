package com.tangbaobao.spider;

import com.tangbaobao.spider.domain.Request;
import com.tangbaobao.spider.engin.ConcurrentEngine;
import com.tangbaobao.spider.engin.SingleEngine;
import com.tangbaobao.spider.parser.CityParser;
import com.tangbaobao.spider.persist.ItemSaver;
import com.tangbaobao.spider.scheduler.SimpleScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {

    @Autowired
    ItemSaver itemSaver;
    @Test
    public void fun1() throws Exception {
        Request request = new Request();
        request.setParseFunc((contents, url) -> CityParser.parseCity(contents));
        request.setUrl("http://www.zhenai.com/zhenghun/aba");
        SingleEngine singleEngine = new SingleEngine(itemSaver);

        singleEngine.run(request);
    }

    @Test
    public void fun2() throws InterruptedException {
        Request request = new Request();
        request.setParseFunc((contents, url) -> CityParser.parseCity(contents));
        request.setUrl("http://www.zhenai.com/zhenghun/aba");
        ConcurrentEngine engine = new ConcurrentEngine();
        engine.setScheduler(new SimpleScheduler());
        engine.setWorkCount(1);
        engine.run(request);
    }


}
