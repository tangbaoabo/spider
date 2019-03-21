package com.tangbaobao.spider.persist.dao;

import com.tangbaobao.spider.domain.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tangxuejun
 * @version 2019-03-19 19:45
 */
@Repository
public interface CrawlerRepository extends ElasticsearchRepository<Item, String> {
}
