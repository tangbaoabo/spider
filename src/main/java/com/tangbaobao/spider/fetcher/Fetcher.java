package com.tangbaobao.spider.fetcher;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author tangxuejun
 * @version 2019-03-18 14:17
 */
public class Fetcher {
    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    public String fetcher(String url) throws Exception {
        HttpGet httpget = new HttpGet(url);
        //携带Header,跳过机器人判断
        httpget.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36");
        String result;
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(httpget)) {
            //如果返回正常则直接返回结果
            if (response != null && response.getStatusLine().getStatusCode()
                    == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                return result;
            }
        }
        throw new Exception("网页中什么也没有");
    }
}
