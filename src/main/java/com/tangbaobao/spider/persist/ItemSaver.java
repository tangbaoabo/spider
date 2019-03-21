package com.tangbaobao.spider.persist;

import com.tangbaobao.spider.domain.Item;
import com.tangbaobao.spider.persist.service.CrawlerService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangxuejun
 * @version 2019-03-19 18:15
 */
@Service
@NoArgsConstructor
@Slf4j
public class ItemSaver {
    @Autowired
    private CrawlerService crawlerService;

    public void saveItem(Item item) {
        crawlerService.save(item);
        log.info(item.toString());
    }
}
