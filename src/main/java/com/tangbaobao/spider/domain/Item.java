package com.tangbaobao.spider.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author tangxuejun
 * @version 2019-03-18 16:38
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "data", type = "zhenai")
public class Item {
    //条目所对应的URL
    String url;
    //每个条目的Id
    @Id
    String id;
    //ES的TYPE
    String type;
    //包含的内容
    Profile payLoad;
}
