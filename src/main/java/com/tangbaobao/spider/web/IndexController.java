package com.tangbaobao.spider.web;

import com.tangbaobao.spider.domain.Item;
import com.tangbaobao.spider.persist.service.CrawlerService;
import com.tangbaobao.spider.web.dto.ItemResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tangxuejun
 * @version 2019-03-20 12:17
 */
@Controller
public class IndexController {
    private final CrawlerService crawlerService;

    @Autowired
    public IndexController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @RequestMapping("/search")
    public String index(@RequestParam(value = "question", defaultValue = "_search")String question,
                        ModelMap modelMap,
                        @RequestParam(value = "from", defaultValue = "0") Integer from) {

        Page<Item> pagedItem = crawlerService.findByCondition(StringUtils.trimToEmpty(question), from);
        System.out.println(question);
        System.out.println("from");
        List<Item> collect = pagedItem.get().collect(Collectors.toList());
        ItemResult itemResult = new ItemResult();
        itemResult.setStart(from);
        itemResult.setCount((int) pagedItem.getTotalElements());
        itemResult.setItemList(collect);
        itemResult.setQuery(question);
        System.out.println(itemResult);
        itemResult.setPre(from-1);
        itemResult.setNext(from+1);
        modelMap.addAttribute("itemResult", itemResult);
        return "template";
    }
}
