package com.tangbaobao.spider.persist.service;

import com.tangbaobao.spider.domain.Item;
import com.tangbaobao.spider.persist.dao.CrawlerRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * @author tangxuejun
 * @version 2019-03-19 19:47
 */
@Service
public class CrawlerService {
    private final CrawlerRepository repository;

    @Autowired
    public CrawlerService(CrawlerRepository repository) {
        this.repository = repository;
    }


    public void save(Item item) {
        repository.save(item);
    }

    public Page<Item> findByCondition(String q,int page) {
        //1.创建查询条件，也就是QueryBuild
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(q);

        //3.1方法1,通过reporitory执行查询,获得有Page包装了的结果集

        return repository.search(queryStringQueryBuilder, PageRequest.of(page, 10));

    }


}
